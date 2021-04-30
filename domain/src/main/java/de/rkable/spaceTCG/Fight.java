package de.rkable.spaceTCG;

import java.util.ArrayList;
import java.util.List;

import de.rkable.spaceTCG.card.gameStats.change.DamageAppliedToOpponent;
import de.rkable.spaceTCG.display.DeckDisplay;
import de.rkable.spaceTCG.display.FightDisplay;
import de.rkable.spaceTCG.display.FightDisplayBuilder;

public class Fight {

	private List<FightEventListener> listeners = new ArrayList<>();
	private Ship player;
	private Ship opponent;
	private GameDeck deck;

	public Fight(Ship player, Ship opponent, GameDeck deck) {
		this.player = player;
		this.opponent = opponent;
		this.deck = deck;
		deck.drawCard();
		deck.drawCard();
		deck.drawCard();
		deck.drawCard();
	}

	public boolean hasUserLost() {
		return !player.isAlive();
	}

	public boolean hasUserWon() {
		return !opponent.isAlive();
	}

	public FightDisplay display() {
		FightDisplayBuilder builder = new FightDisplayBuilder();
		player.display(builder.player());
		opponent.display(builder.opponent());
		
		builder.setDeckDisplay(new DeckDisplay(
				deck.getDrawnCards().get(0),
				deck.getDrawnCards().get(1),
				deck.getDrawnCards().get(2),
				deck.getDrawnCards().get(3)
				));
		
		return builder.build();
	}

	public List<Card> getDrawnCards() {
		return deck.getDrawnCards();
	}

	public void play(Card card) {
		List<GameStatChange> changes = card.play(() -> display());
		for(GameStatChange change : changes) {
			if (change instanceof DamageAppliedToOpponent) {
				DamageAppliedToOpponent damage = (DamageAppliedToOpponent) change;
				opponent.process(damage);
			}
		}
		deck.discard(card);
		deck.drawCard();
		
		for (FightEventListener listener : listeners) {
			listener.cardPlayed(card, changes);
		}
	}

	public void addFightEventListener(FightEventListener fightEventListener) {
		listeners.add(fightEventListener);
	}

}
