package de.rkable.spaceTCG;

import java.util.ArrayList;
import java.util.List;

import de.rkable.spaceTCG.display.DeckDisplay;
import de.rkable.spaceTCG.display.FightDisplay;
import de.rkable.spaceTCG.display.FightDisplayBuilder;
import de.rkable.spaceTCG.player.Player;

public class Fight {
	
	private final static int MAX_ENERGY = 3;

	private int playerEnergy = MAX_ENERGY;
	private List<FightEventListener> listeners = new ArrayList<>();
	private Player player;
	private Opponent opponent;
	private HandOf4Cards hand;
	
	public Fight(Player player, Opponent opponent) {
		this(player, opponent, new FightDeck(player.getDeck()));
	}
	
	Fight(Player player, Opponent opponent, FightDeck fightDeck) {
		this.player = player;
		this.opponent = opponent;
		hand = new HandOf4Cards(fightDeck);
	}

	public boolean hasUserLost() {
		return !player.isAlive();
	}

	public boolean hasUserWon() {
		return !opponent.getShip().isAlive();
	}

	public FightDisplay display() {
		FightDisplayBuilder builder = new FightDisplayBuilder();
		player.getShip().display(builder.player());
		opponent.getShip().display(builder.opponent());
		
		builder.setDeckDisplay(new DeckDisplay(
				hand.getDrawnCards().get(0),
				hand.getDrawnCards().get(1),
				hand.getDrawnCards().get(2),
				hand.getDrawnCards().get(3)
				));
		builder.setEnergy(playerEnergy);
		builder.setMaxEnergy(MAX_ENERGY);
		
		return builder.build();
	}

	public List<Card> getDrawnCards() {
		return hand.getDrawnCards();
	}

	public void play(Card card) {
		if (playerEnergy <= 0) {
			throw new RuntimeException("Cannot play a card without energy");
		}
		playerEnergy--;
		List<GameStateChange> changes = card.play(() -> display());
		hand.discardAndDrawCard(card);
		
		List<GameStateChange> appliedChanges = processChanges(changes);
		
		// inform listeners
		for (FightEventListener listener : new ArrayList<>(listeners)) {
			listener.cardPlayed(card, appliedChanges);
			if (!opponent.isAlive()) {
				listener.victory();
			}
		}
	}

	public void addFightEventListener(FightEventListener fightEventListener) {
		listeners.add(fightEventListener);
	}

	public void removeFightEventListener(FightEventListener fightEventListener) {
		listeners.remove(fightEventListener);
	}

	public void endTurn() {
		List<GameStateChange> changes = opponent.performNextAction(() -> display());
		List<GameStateChange> appliedChanges = processChanges(changes);
		for (FightEventListener listener : new ArrayList<>(listeners)) {
			listener.opponentPlayed(appliedChanges);
			if (!player.isAlive()) {
				listener.defeat();
			} else if (!opponent.isAlive()) {
				listener.victory();
			}
		}
		hand.discardAllAndDrawNew();
		playerEnergy = MAX_ENERGY;
	}

	private List<GameStateChange> processChanges(List<GameStateChange> changes) {
		
		List<GameStateChange> appliedChanges = new ArrayList<>();
		for (GameStateChange change : changes) {
			appliedChanges.add(change);
			player.process(change);
			if (!player.isAlive()) {
				break;
			}
			opponent.process(change);
			if (!opponent.isAlive()) {
				break;
			}
		}

		return appliedChanges;
	}

	public int getMaxEnergy() {
		return 3;
	}

	public int getEnergy() {
		return playerEnergy;
	}

}
