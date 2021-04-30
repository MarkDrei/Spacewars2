package de.rkable.spaceTCG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A {@link GameDeck} is the deck during a single game / combat. It has piles and is alterable. 
 *
 */
public class GameDeck {
	
	private final List<Card> drawPile = new ArrayList<>();
	private final List<Card> discardPile = new ArrayList<>();
	private final List<Card> drawnCards = new LinkedList<>();

	public void addCardToDiscardPile(Card card) {
		discardPile.add(card);
	}
	
	public int getDrawPileSize() {
		return drawPile.size();
	}

	public Card drawCard() {
		if (drawPile.isEmpty()) {
			shuffleDiscardPileIntoDrawPile();
		}
		Card card = drawPile.remove(drawPile.size() - 1);
		drawnCards.add(card);
		return card;
	}

	private void shuffleDiscardPileIntoDrawPile() {
		drawPile.addAll(discardPile);
		Collections.shuffle(drawPile);
		discardPile.clear();
	}

	public void discard(Card discardedCard) {
		if (!drawnCards.contains(discardedCard)) {
			throw new RuntimeException("Card cannot be discarded as it was not drawn before");
		}
		drawnCards.remove(discardedCard);
		discardPile.add(discardedCard);
	}

	public int getDiscardPileSize() {
		return discardPile.size();
	}

	public List<Card> getDrawnCards() {
		return drawnCards;
	}

}
