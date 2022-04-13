package de.rkable.spaceTCG.display;

import de.rkable.spaceTCG.Card;

/**
 * Immutable class which describes the drawn cards.
 *
 */
public class DeckDisplay {
	
	public final Card card1;
	public final Card card2;
	public final Card card3;
	public final Card card4;

	public DeckDisplay(Card card1, Card card2, Card card3, Card card4) {
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		this.card4 = card4;
	}

}
