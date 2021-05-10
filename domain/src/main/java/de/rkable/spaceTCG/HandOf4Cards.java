package de.rkable.spaceTCG;

import java.util.Arrays;
import java.util.List;

/**
 * Wrapper around a deck which ensures that after discarding a card, the new card
 * is at the position of the old one.
 *
 */
public class HandOf4Cards {

	private FightDeck deck;
	private Card[] drawnCards = new Card[4];

	public HandOf4Cards(FightDeck gameDeck) {
		this.deck = gameDeck;
		drawNewCards();
	}

	public List<Card> getDrawnCards() {
		return Arrays.asList(drawnCards);
	}

	public void discardAndDrawCard(Card cardToDiscard) {
		for (int i = 0; i < drawnCards.length; i++) {
			if (drawnCards[i] == cardToDiscard) {
				deck.discard(cardToDiscard);
				drawnCards[i] = deck.drawCard();
				return;
			}
		}
		throw new RuntimeException("Card has not been drawn before, cannot discard it");
		
	}

	public void discardAllAndDrawNew() {
		deck.discard(drawnCards[0]);
		deck.discard(drawnCards[1]);
		deck.discard(drawnCards[2]);
		deck.discard(drawnCards[3]);
		drawNewCards();
	}
	
	private void drawNewCards() {
		drawnCards[0] = deck.drawCard();
		drawnCards[1] = deck.drawCard();
		drawnCards[2] = deck.drawCard();
		drawnCards[3] = deck.drawCard();
	}

}
