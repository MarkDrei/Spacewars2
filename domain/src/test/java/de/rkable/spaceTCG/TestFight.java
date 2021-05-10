package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.rkable.spaceTCG.card.BasicLaser;
import de.rkable.spaceTCG.card.BurstLaser;
import de.rkable.spaceTCG.display.FightDisplay;
import de.rkable.spaceTCG.player.Player;
import de.rkable.spaceTCG.player.PlayerDeck;

public class TestFight {
	
	private Fight fight;
	private Opponent opponentMock;
	private PlayerDeck gameDeckMock;
	private Player player;
	private FightEventListener eventListenerMock;

	@SuppressWarnings("boxing")
	@BeforeEach
	public void setup() {
		opponentMock = mock(Opponent.class);
		when(opponentMock.getShip()).thenReturn(new Ship(2));
		gameDeckMock = new PlayerDeck(new BasicLaser(), new BasicLaser(), new BasicLaser(), new BasicLaser());
//		gameDeckMock = mock(PlayerDeck.class);
//		when(gameDeckMock.getAllCards())
//			.thenReturn(Arrays.asList(new BasicLaser(), new BasicLaser(), new BasicLaser(), new BasicLaser()));
		player = spy(new Player(new Ship(1), gameDeckMock));
		fight = new Fight(player, opponentMock);
		
		when(opponentMock.isAlive()).thenReturn(true);
		when(player.isAlive()).thenReturn(true);
		
		eventListenerMock = mock(FightEventListener.class);
		fight.addFightEventListener(eventListenerMock);
	}
	
	@Test
	public void hasUserLost_whenShipIsAlive_isFalse() {
		assertFalse(fight.hasUserLost());
	}
	
	@Test
	public void hasUserLost_whenShipIsNotAlive_isTrue() {
		fight = new Fight(new Player(new Ship(0), gameDeckMock), opponentMock);
		assertTrue(fight.hasUserLost());
	}
	
	@Test
	public void hasUserWon_whenOpponentIsAlive_isFalse() {
		assertFalse(fight.hasUserWon());
	}
	
	@Test
	public void hasUserWon_whenOpponentIsNotAlive_isTrue() {
		when(opponentMock.getShip()).thenReturn(new Ship(0));
		assertTrue(fight.hasUserWon());
	}
	
	@Test
	public void getEnergy_defaultsTo3() {
		assertEquals(3, fight.getMaxEnergy());
		assertEquals(3, fight.getEnergy());
	}
	
	@SuppressWarnings("boxing")
	@Test
	public void playCard_withMultipleEffects_appliesOnlyEffectsUntilVictory() {
		gameDeckMock = new PlayerDeck(new BurstLaser(), new BurstLaser(), new BurstLaser(), new BurstLaser());
		fight = new Fight(new Player(new Ship(2), gameDeckMock), opponentMock);
		fight.addFightEventListener(eventListenerMock);
		when(player.isAlive()).thenReturn(true);
		when(opponentMock.isAlive()).thenReturn(true, false);
		
		// execute
		fight.play(fight.getDrawnCards().get(0)); // plays a burst laser
		
		// verify
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArgumentCaptor<List<GameStateChange>> changes = ArgumentCaptor.forClass((Class) List.class);
		verify(eventListenerMock, times(1)).cardPlayed(any(), changes.capture());
		verify(eventListenerMock).victory();
		assertEquals(2, changes.getValue().size());
	}
	
	@Test
	public void display_givesDisplay() {
		FightDisplay display = fight.display();
		assertNotNull(display);
		assertEquals(1, display.player.hull);
		assertEquals(3, display.energy);
		assertEquals(3, display.maxEnergy);
	}
	
	@Test
	public void endTurn_withOpponentActions_forwardsThoseActions() {
		GameStateChange stateChange1 = mock(GameStateChange.class);
		GameStateChange stateChange2 = mock(GameStateChange.class);
		when(opponentMock.performNextAction(any())).thenReturn(Arrays.asList(stateChange1, stateChange2));
		
		fight.endTurn();
		
		InOrder inOrder = inOrder(opponentMock, player);
		
		// verify
		inOrder.verify(opponentMock).performNextAction(any());
		
		//  first state change
		inOrder.verify(player).process(stateChange1);
		inOrder.verify(player).isAlive();
		inOrder.verify(opponentMock).process(stateChange1);
		inOrder.verify(opponentMock).isAlive();
		
		//  second state change
		inOrder.verify(player).process(stateChange2);
		inOrder.verify(player).isAlive();
		inOrder.verify(opponentMock).process(stateChange2);
		inOrder.verify(opponentMock).isAlive();
		
		verify(eventListenerMock).opponentPlayed(Arrays.asList(stateChange1, stateChange2));
	}
	
	@SuppressWarnings("boxing")
	@Test
	public void endTurn_killBothPlayers_endsInDefeat() {
		GameStateChange stateChange1 = mock(GameStateChange.class);
		when(opponentMock.performNextAction(any())).thenReturn(Arrays.asList(stateChange1));
		when(player.isAlive()).thenReturn(false);
		when(opponentMock.isAlive()).thenReturn(false);
		
		fight.endTurn();
		
		InOrder inOrder = inOrder(opponentMock, player);
		
		// verify
		inOrder.verify(opponentMock).performNextAction(any());
		
		//  first state change
		inOrder.verify(player).process(stateChange1);
		inOrder.verify(player, times(2)).isAlive();
		verify(eventListenerMock).defeat();
		verify(eventListenerMock, never()).victory();
	}
	
	@SuppressWarnings("boxing")
	@Test
	public void endTurn_killingOnlyTheOpponent_endsInVictory() {
		GameStateChange stateChange1 = mock(GameStateChange.class);
		when(opponentMock.performNextAction(any())).thenReturn(Arrays.asList(stateChange1));
		when(opponentMock.isAlive()).thenReturn(false);
		
		fight.endTurn();
		
		InOrder inOrder = inOrder(opponentMock, player);
		
		// verify
		inOrder.verify(opponentMock).performNextAction(any());
		
		//  first state change
		inOrder.verify(player).process(stateChange1);
		inOrder.verify(player).isAlive();
		inOrder.verify(opponentMock).process(stateChange1);
		inOrder.verify(opponentMock).isAlive();
		verify(eventListenerMock).victory();
	}
	
	@Nested
	@DisplayName("with default 4 card laser deck")
	public class withDefaultLaserDeck {
		
		private FightDeck fightDeckMock;
		
		@BeforeEach
		public void setupFight() {
			fightDeckMock = mock(FightDeck.class);
			when(fightDeckMock.drawCard()).thenAnswer(new Answer<Card>() {

				@Override
				public Card answer(InvocationOnMock invocation) throws Throwable {
					return new BasicLaser();
				}
			});
//			player = new Player(new Ship(1), gameDeckMock);
			
			when(opponentMock.getShip()).thenReturn(new Ship(5));
			fight = new Fight(player, opponentMock, fightDeckMock);
			fight.addFightEventListener(eventListenerMock);
		}
		
		
		@Test
		public void getDrawnCards_gives4Cards() {
			List<Card> cards = fight.getDrawnCards();
			assertEquals(4, cards.size());
			verify(fightDeckMock, times(4)).drawCard();
		}
		
		@Test
		public void playCard_playsTheCard() {
			Card card = fight.getDrawnCards().get(0);
			
			// execute
			fight.play(card);
			
			// verify
			verify(fightDeckMock).discard(card);
			verify(fightDeckMock, times(5)).drawCard();
		}
		
		@Test
		public void playCard_twoTimes_accumulatesUpdates() {
			// execute
			fight.play(fight.getDrawnCards().get(0));
			fight.play(fight.getDrawnCards().get(0));
			
			// verify
			verify(fightDeckMock, times(2)).discard(any());
			verify(fightDeckMock, times(6)).drawCard();
		}
		
		@SuppressWarnings("boxing")
		@Test
		public void playCard_untilVictory_givesNotification() {
			when(opponentMock.isAlive()).thenReturn(true);
			
			// execute
			fight.play(fight.getDrawnCards().get(0));
			when(opponentMock.isAlive()).thenReturn(false);
			fight.play(fight.getDrawnCards().get(0));
			
			verify(eventListenerMock).victory();
		}
		
		@Test
		public void addFightEventListener_whenCardIsPlayed_getsInformed() {
			class Listener implements FightEventListener {
				
				Card listenerCard = null;
				List<GameStateChange> changes = null;
				
				@Override
				public void cardPlayed(Card card, List<GameStateChange> changesListener) {
					listenerCard = card;
					changes = changesListener;
				}
				
				@Override
				public void victory() { /* nop */ }
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
		
		@Test
		public void removeFightEventListener_withMultipleListener_isSaveAgainstConcurrentModifications() {
			class DeregisterUponEvent implements FightEventListener {
				@Override
				public void cardPlayed(Card card, List<GameStateChange> changes) {
					fight.removeFightEventListener(this);
				}
			}
			
			fight.addFightEventListener(new DeregisterUponEvent());
			fight.addFightEventListener(new DeregisterUponEvent());
			fight.addFightEventListener(new DeregisterUponEvent());

			fight.play(fight.getDrawnCards().get(0));
		}
		
		
		@Test
		public void endTurn_discardsHandAndDrawsNewCards() {
			fight.endTurn();
			verify(fightDeckMock, times(4)).discard(any());
		}
		
		@Test
		public void getEnergy_afterCardWasPlayed_isDecreased() {
			fight.play(fight.getDrawnCards().get(0));
			assertEquals(3, fight.getMaxEnergy());
			assertEquals(2, fight.getEnergy());
		}
		
		@Test
		public void getEnergy_afterCardWasPlayed_isDecreasedOnDisplay() {
			fight.play(fight.getDrawnCards().get(0));
			assertEquals(3, fight.display().maxEnergy);
			assertEquals(2, fight.display().energy);
		}
		
		@Test
		public void playCard_withNoEnergy_throws() {
			fight.play(fight.getDrawnCards().get(0));
			fight.play(fight.getDrawnCards().get(0));
			fight.play(fight.getDrawnCards().get(0));
			assertThrows(RuntimeException.class, () -> fight.play(fight.getDrawnCards().get(0)));
		}
		
		@Test
		public void endTurn_resetsTheEnergy() {
			fight.play(fight.getDrawnCards().get(0));
			fight.play(fight.getDrawnCards().get(0));
			fight.endTurn();
			assertEquals(3, fight.getEnergy());
		}
	}
	
}
