package de.rkable.spaceTCG;

import java.util.Arrays;
import java.util.List;

public class HandOf4Cards {

	private GameDeck deck;
	private Card[] drawnCards = new Card[4];

	public HandOf4Cards(GameDeck gameDeck) {
		this.deck = gameDeck;
		drawnCards[0] = deck.drawCard();
		drawnCards[1] = deck.drawCard();
		drawnCards[2] = deck.drawCard();
		drawnCards[3] = deck.drawCard();
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

}
