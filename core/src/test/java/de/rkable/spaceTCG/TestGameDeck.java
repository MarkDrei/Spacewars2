package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestGameDeck {
	
	@Test
	public void getPileSizes_onEmptyDeck_isZero() {
		GameDeck deck = new GameDeck();
		assertEquals(0, deck.getDrawPileSize());
		assertEquals(0, deck.getDiscardPileSize());
	}
	
	@Test
	public void getDrawPileSize_givesSize() {
		GameDeck deck = new GameDeck();
		deck.addCardToDiscardPile(getDummyCard());
		assertEquals(0, deck.getDrawPileSize());
		assertEquals(1, deck.getDiscardPileSize());
	}

	private Card getDummyCard() {
		return new Card() {
			@Override
			public String getName() {
				return "";
			}
		};
	}
	
	@Test
	public void drawCard_onEmptyDeck_throws() {
		GameDeck deck = new GameDeck();
		assertThrows(RuntimeException.class, () -> deck.drawCard());
	}

	@Test
	public void drawCard_onDeckWithOneCard_givesCard() {
		GameDeck deck = new GameDeck();
		Card card = getDummyCard();
		deck.addCardToDiscardPile(card);
		Card drawnCard = deck.drawCard();
		assertSame(card, drawnCard);
	}
	
	@Test
	public void drawCard_threeTimesOnDeckWithOneCard_throws() {
		GameDeck deck = new GameDeck();
		deck.addCardToDiscardPile(getDummyCard());
		deck.addCardToDiscardPile(getDummyCard());
		deck.drawCard();
		deck.drawCard();
		assertThrows(RuntimeException.class, () -> deck.drawCard());
	}
	
	@Test
	public void discardCard_forCardWhichIsNotDrawn_throws() {
		GameDeck deck = new GameDeck();
		assertThrows(RuntimeException.class, () -> deck.discard(getDummyCard()));
	}
	
	@Test
	public void discardCard_allowsToDrawCardAgain() {
		GameDeck deck = new GameDeck();
		deck.addCardToDiscardPile(getDummyCard());
		Card drawnCard = deck.drawCard();
		deck.discard(drawnCard);
		assertEquals(drawnCard, deck.drawCard());
	}
	
	@Test
	public void discardCard_twice_throws() {
		GameDeck deck = new GameDeck();
		deck.addCardToDiscardPile(getDummyCard());
		Card drawnCard = deck.drawCard();
		deck.discard(drawnCard);
		assertThrows(RuntimeException.class, () -> deck.discard(drawnCard));
	}
	
	@Nested
	@DisplayName("Given a deck with one cards")
	public class OneCard {
		GameDeck deck = new GameDeck();
		Card card1 = getDummyCard();
		
		@BeforeEach
		public void setup() {
			deck.addCardToDiscardPile(card1);
		}
		
		@Test
		public void discardCard_leadsToCorrectDiscardPileSize() {
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
			public void drawingCards_eventuallyShufflesInTheDiscardPile() {
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
