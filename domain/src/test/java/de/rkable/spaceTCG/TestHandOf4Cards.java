package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.rkable.spaceTCG.card.BasicLaser;

/**
 * Four cards, hold in a hand.
 * Wrapper around a deck, which always draws 4 cards.
 *
 */
public class TestHandOf4Cards {
	
	private GameDeck gameDeck;
	private HandOf4Cards hand;
	
	@BeforeEach
	public void setupFight() {
		gameDeck = mock(GameDeck.class);
		when(gameDeck.drawCard()).thenAnswer(new Answer<Card>() {

			@Override
			public Card answer(InvocationOnMock invocation) throws Throwable {
				return new BasicLaser();
			}
		});
		hand = new HandOf4Cards(gameDeck);
	}
	
	@Test
	public void initialHand_has4Cards() {
		List<Card> cards = hand.getDrawnCards();
		assertEquals(4, cards.size());
	}
	
	@Test
	public void discardAndDrawCard__replacesCardAtRightIndex() {
		Card card0 = hand.getDrawnCards().get(0);
		Card card1 = hand.getDrawnCards().get(1);
		Card card2 = hand.getDrawnCards().get(2);
		Card card3 = hand.getDrawnCards().get(3);
		
		hand.discardAndDrawCard(card2);
		assertEquals(card0, hand.getDrawnCards().get(0));
		assertEquals(card1, hand.getDrawnCards().get(1));
		assertNotEquals(card2, hand.getDrawnCards().get(2));
		assertEquals(card3, hand.getDrawnCards().get(3));
	}
	
	@Test
	public void discardAndDrawCard_whenCardWasNeverDrawn_throws() {
		assertThrows(RuntimeException.class, () -> hand.discardAndDrawCard(new BasicLaser()));
	}

}
