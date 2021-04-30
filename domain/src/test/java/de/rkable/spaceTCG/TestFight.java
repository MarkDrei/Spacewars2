package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.card.BasicLaser;
import de.rkable.spaceTCG.display.FightDisplay;

public class TestFight {
	
	@Test
	public void hasUserLost_whenShipIsAlive_isFalse() {
		Fight fight = new Fight(new Ship(1), new Ship(1), mock(GameDeck.class));
		assertFalse(fight.hasUserLost());
	}
	
	@Test
	public void hasUserLost_whenShipIsNotAlive_isTrue() {
		Fight fight = new Fight(new Ship(0), new Ship(1), mock(GameDeck.class));
		assertTrue(fight.hasUserLost());
	}
	
	@Test
	public void hasUserWon_whenOpponentIsAlive_isFalse() {
		Fight fight = new Fight(new Ship(1), new Ship(1), mock(GameDeck.class));
		assertFalse(fight.hasUserWon());
	}
	
	@Test
	public void hasUserWon_whenOpponentIsNotAlive_isTrue() {
		Fight fight = new Fight(new Ship(1), new Ship(0), mock(GameDeck.class));
		assertTrue(fight.hasUserWon());
	}
	
	
	@Test
	public void display_givesDisplay() {
		Fight fight = new Fight(new Ship(1), new Ship(0), mock(GameDeck.class, RETURNS_DEEP_STUBS));
		FightDisplay display = fight.display();
		assertNotNull(display);
		assertEquals(1, display.player.hull);
	}
	
	@Nested
	@DisplayName("with default 4 card laser deck")
	public class withDefaultLaserDeck {
		
		private GameDeck gameDeck;
		private Fight fight;
		
		@BeforeEach
		public void setupFight() {
			gameDeck = mock(GameDeck.class);
			when(gameDeck.drawCard()).thenReturn(null);
			when(gameDeck.getDrawnCards()).thenReturn(Arrays.asList(
					new BasicLaser(),
					new BasicLaser(),
					new BasicLaser(),
					new BasicLaser()
					));
			fight = new Fight(new Ship(5), new Ship(5), gameDeck);
		}
		
		@Test
		public void getDrawnCards_gives4Cards() {
			List<Card> cards = fight.getDrawnCards();
			assertEquals(4, cards.size());
			verify(gameDeck, times(4)).drawCard();
		}
		
		@Test
		public void playCard_playsTheCard() {
			Card card = fight.getDrawnCards().get(0);
			
			// execute
			fight.play(card);
			
			// verify
			FightDisplay display = fight.display();
			assertEquals(2, display.opponent.hull);
			verify(gameDeck).discard(card);
			verify(gameDeck, times(5)).drawCard();
		}
		
		@Test
		public void playCard_twoTimes_accumulatesUpdates() {
			Card card = fight.getDrawnCards().get(0);
			
			// execute
			fight.play(card);
			fight.play(card);
			
			// verify
			FightDisplay display = fight.display();
			assertEquals(-1, display.opponent.hull);
			verify(gameDeck, times(2)).discard(card);
			verify(gameDeck, times(6)).drawCard();
		}
		
		@Test
		public void addFightEventListener_whenCardIsPlayed_getsInformed() {
			class Listener implements FightEventListener {
				
				Card listenerCard = null;
				List<GameStatChange> changes = null;
				
				@Override
				public void cardPlayed(Card card, List<GameStatChange> changesListener) {
					listenerCard = card;
					changes = changesListener;
					
				}
			}
			Listener listener = new Listener();
			
			// execute
			fight.addFightEventListener(listener);
			Card playedCard = fight.getDrawnCards().get(0);
			fight.play(playedCard);
			
			// verify
			assertSame(playedCard, listener.listenerCard);
			assertEquals(1, listener.changes.size());
		}
	}
	

}
