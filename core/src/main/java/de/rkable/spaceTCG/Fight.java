package de.rkable.spaceTCG;

import java.util.List;

import de.rkable.spaceTCG.display.DeckDisplay;
import de.rkable.spaceTCG.display.FightDisplay;
import de.rkable.spaceTCG.display.FightDisplayBuilder;

public class Fight {

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

}
