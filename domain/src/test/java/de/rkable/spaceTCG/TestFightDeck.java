package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.rkable.spaceTCG.player.PlayerDeck;

public class TestFightDeck {
	
	@Test
	public void constructorFromPlayerDeck() {
		PlayerDeck playerDeck = mock(PlayerDeck.class);
		when(playerDeck.getAllCards()).thenReturn(Arrays.asList(mock(Card.class), mock(Card.class)));
		FightDeck deck = new FightDeck(playerDeck);
		assertEquals(2, deck.getDiscardPileSize());
	}
	
	@Test
	public void getPileSizes_onEmptyDeck_isZero() {
		FightDeck deck = new FightDeck();
		assertEquals(0, deck.getDrawPileSize());
		assertEquals(0, deck.getDiscardPileSize());
	}
	
	@Test
	public void getDrawPileSize_givesSize() {
		FightDeck deck = new FightDeck();
		deck.addCardToDiscardPile(getDummyCard());
		assertEquals(0, deck.getDrawPileSize());
		assertEquals(1, deck.getDiscardPileSize());
	}

	private Card getDummyCard() {
		return Mockito.mock(Card.class);
	}
	
	@Test
	public void drawCard_onEmptyDeck_throws() {
		FightDeck deck = new FightDeck();
		assertThrows(RuntimeException.class, () -> deck.drawCard());
	}

	@Test
	public void drawCard_onDeckWithOneCard_givesCard() {
		FightDeck deck = new FightDeck();
		Card card = getDummyCard();
		deck.addCardToDiscardPile(card);
		Card drawnCard = deck.drawCard();
		assertSame(card, drawnCard);
	}
	
	@Test
	public void drawCard_threeTimesOnDeckWithOneCard_throws() {
		FightDeck deck = new FightDeck();
		deck.addCardToDiscardPile(getDummyCard());
		deck.addCardToDiscardPile(getDummyCard());
		deck.drawCard();
		deck.drawCard();
		assertThrows(RuntimeException.class, () -> deck.drawCard());
	}
	
	@Test
	public void discardCard_forCardWhichIsNotDrawn_throws() {
		FightDeck deck = new FightDeck();
		assertThrows(IllegalUserOperation.class, () -> deck.discard(getDummyCard()));
	}
	
	@Test
	public void discardCard_allowsToDrawCardAgain() throws IllegalUserOperation {
		FightDeck deck = new FightDeck();
		deck.addCardToDiscardPile(getDummyCard());
		Card drawnCard = deck.drawCard();
		deck.discard(drawnCard);
		assertEquals(drawnCard, deck.drawCard());
	}
	
	@Test
	public void discardCard_twice_throws() throws IllegalUserOperation {
		FightDeck deck = new FightDeck();
		deck.addCardToDiscardPile(getDummyCard());
		Card drawnCard = deck.drawCard();
		deck.discard(drawnCard);
		assertThrows(IllegalUserOperation.class, () -> deck.discard(drawnCard));
	}
	
	@Nested
	@DisplayName("Given a deck with one cards")
	public class OneCard {
		FightDeck deck = new FightDeck();
		Card card1 = getDummyCard();
		
		@BeforeEach
		public void setup() {
			deck.addCardToDiscardPile(card1);
		}
		
		@Test
		public void discardCard_leadsToCorrectDiscardPileSize() throws IllegalUserOperation {
			assertEquals(1, deck.getDiscardPileSize());
			Card drawCard = deck.drawCard();
			assertEquals(0, deck.getDiscardPileSize());
			deck.discard(drawCard);
			assertEquals(1, deck.getDiscardPileSize());
		}
		
		@Nested
		@DisplayName("Given a deck with two cards")
		public class TwoCards {
			
			Card card2 = getDummyCard();
			
			@BeforeEach
			public void setup() {
				deck.addCardToDiscardPile(card2);
			}
			
			@Test
			public void drawingCards_eventuallyShufflesInTheDiscardPile() throws IllegalUserOperation {
				Card drawnCard = deck.drawCard();
				assertEquals(1, deck.getDrawPileSize());
				deck.discard(drawnCard);
				assertEquals(1, deck.getDiscardPileSize());
				assertEquals(1, deck.getDrawPileSize());
				
				drawnCard = deck.drawCard();
				assertEquals(1, deck.getDiscardPileSize());
				deck.discard(drawnCard);
				assertEquals(2, deck.getDiscardPileSize());
				assertEquals(0, deck.getDrawPileSize());
				
				// now the shuffle is expected
				deck.drawCard();
				assertEquals(0, deck.getDiscardPileSize());
				assertEquals(1, deck.getDrawPileSize());
			}
			
			@Test
			public void drawnCards_givesDrawnCards() {
				deck.drawCard();
				deck.drawCard();
				
				List<Card> cards = deck.getDrawnCards();
				assertEquals(2, cards.size());
				assertTrue(cards.contains(card1));
				assertTrue(cards.contains(card2));
			}
			
		}
	}
	
	

}
