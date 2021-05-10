package de.rkable.spaceTCG.player;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.Card;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

public class TestPlayerDeck {

	@Test
	public void getDeckSize_givesNumberOfCards() {
		PlayerDeck playerDeck = new PlayerDeck(mock(Card.class), mock(Card.class));
		assertEquals(2, playerDeck.getDeckSize());
	}
	
	@Test
	public void getAllCards_givesCards() {
		Card card1 = mock(Card.class);
		Card card2 = mock(Card.class);
		Card card3 = mock(Card.class);
		PlayerDeck playerDeck = new PlayerDeck(card1, card2, card3);
		List<Card> cards = playerDeck.getAllCards();
		assertEquals(3, cards.size());
		assertTrue(cards.contains(card1));
		assertTrue(cards.contains(card2));
		assertTrue(cards.contains(card3));
	}
}
