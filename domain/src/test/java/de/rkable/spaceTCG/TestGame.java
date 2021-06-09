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

import de.rkable.spaceTCG.map.VisitOpponentAction;
import de.rkable.spaceTCG.map.Waypoint;
import de.rkable.spaceTCG.map.WorldMap;
import de.rkable.spaceTCG.player.Player;
import de.rkable.spaceTCG.player.PlayerDeck;

public class TestGame {
	
	private WorldMap worldMap;
	private Player player;
	private Game game;
	private FightFactory fightFactory;

	@BeforeEach
	public void setup() {
		worldMap = mock(WorldMap.class);
		player = mock(Player.class);
		fightFactory = mock(FightFactory.class, RETURNS_DEEP_STUBS);
		game = new Game(player, fightFactory);
		game.setWorldMap(worldMap);
	}
	
	@Test
	public void getWorldMap() {
		game.setWorldMap(worldMap);
		assertSame(worldMap, game.getWorldMap());
	}
	
	@Test
	public void getPlayer() {
		assertSame(player, game.getPlayer());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void addGameListener_and_removeGameListener() {
		GameListener gameListener1 = mock(GameListener.class);
		GameListener gameListener2 = mock(GameListener.class);
		
		game.addGameListener(gameListener1);
		game.addGameListener(gameListener2);
		
		game.fightStarted(mock(Opponent.class));
		
		verify(gameListener1).fightInitiated(any());
		game.removeGameListener(gameListener2);
		verify(gameListener2).fightInitiated(any());
		
		reset(gameListener1, gameListener2);
		
		game.victory(mock(List.class));
		game.fightStarted(mock(Opponent.class));
		verify(gameListener1).fightInitiated(any());
		verifyNoInteractions(gameListener2);
	}
	
	@Test
	public void fightStarted_callsFightFactory() {
		game = new Game(player, fightFactory);
		Opponent opponent = mock(Opponent.class);
		game.fightStarted(opponent);
		verify(fightFactory).createFight(player, opponent);
	}
	
	@Test
	public void fightStarted_registeresGameAsFightEventListener() {
		game.setWorldMap(worldMap);
		Fight fight = mock(Fight.class);
		when(fightFactory.createFight(any(), any())).thenReturn(fight);
		
		game.fightStarted(mock(Opponent.class));
		
		verify(fight).addFightEventListener(game);
	}
	
	@Test
	public void addCardFromReward_throws() {
		assertThrows(IllegalUserOperation.class, () -> game.pickRewardCard(mock(Card.class)));
	}
	
	@Test
	public void addCardFromReward_afterVictoryWhenCardIsNotAReward_throws() {
		Card card1 = mock(Card.class);
		Card card2 = mock(Card.class);
		game.victory(Arrays.asList(card1));
		PlayerDeck playerDeck = mock(PlayerDeck.class);
		when(player.getDeck()).thenReturn(playerDeck);
		
		assertThrows(IllegalUserOperation.class, () -> game.pickRewardCard(card2));
	}
	
	@Test
	public void addCardFromReward_afterVictory_addsCard() throws IllegalUserOperation {
		Card card = mock(Card.class);
		game.victory(Arrays.asList(card));
		PlayerDeck playerDeck = mock(PlayerDeck.class);
		when(player.getDeck()).thenReturn(playerDeck);
		
		game.pickRewardCard(card);
		
		verify(player).getDeck();
		verify(playerDeck).addCard(card);
	}
	
	@SuppressWarnings("boxing")
	@Test
	public void visit_whenWaypointIsNotReachable_throws() {
		when(worldMap.isReachable(any())).thenReturn(false);
		
		assertThrows(IllegalUserOperation.class, () -> game.visit(mock(Waypoint.class)));
	}
	
	@Test
	public void setWorldMap_triggersListeners() {
		GameListener gameListener = mock(GameListener.class);
		game.addGameListener(gameListener);
		WorldMap worldMap2 = mock(WorldMap.class);
		
		game.setWorldMap(worldMap2);
		
		verify(gameListener).mapChanged(worldMap2);
	}
	
	
	@Nested
	@DisplayName("with real player object")
	public class withRealPlayer {
		
		@BeforeEach
		public void setup() {
			player = new Player(mock(Ship.class), mock(PlayerDeck.class));
			game = new Game(player, fightFactory);
			game.setWorldMap(worldMap);
		}
		
		@SuppressWarnings("boxing")
		@Test
		public void visit_travelsOnMap() throws IllegalUserOperation {
			game.setWorldMap(worldMap);
			when(worldMap.isReachable(any())).thenReturn(true);
			
			Waypoint waypoint = mock(Waypoint.class);
			game.visit(waypoint);
			
			verify(worldMap).travel(waypoint);
		}
		
		/**
		 * This is more a component test style
		 * @throws IllegalUserOperation 
		 */
		@Test
		public void visit_whenWaypointHasFight_initiatesTheFight() throws IllegalUserOperation {
			Waypoint waypointMock = new Waypoint("waypoint", new VisitOpponentAction(game, mock(Opponent.class)));
			Waypoint start = new Waypoint("waypoint", waypointMock);
			game.setWorldMap(new WorldMap(start));
			GameListener gameListener = mock(GameListener.class);
			game.addGameListener(gameListener);
			
			// execute 
			game.visit(waypointMock);
			
			//verify
			gameListener.fightInitiated(any(Fight.class));
		}
		
		@Test
		public void fightStarted_callsFightInitiated() {
			GameListener gameListener = mock(GameListener.class);
			game.addGameListener(gameListener);
			
			game.fightStarted(mock(Opponent.class));
			
			verify(gameListener).fightInitiated(any(Fight.class));
		}
		
		@Test
		public void fightStarted_whenFightIsAlreadyInProgress_throws() {
			when(fightFactory.createFight(any(), any())).thenReturn(mock(Fight.class));
			game.fightStarted(mock(Opponent.class));
			assertThrows(RuntimeException.class, () -> game.fightStarted(mock(Opponent.class)));
		}
		
		@SuppressWarnings("unchecked")
		@Test
		public void fightStarted_afterPreviousFightHasFinished_works() {
			game.fightStarted(mock(Opponent.class));
			game.victory(mock(List.class));
			game.fightStarted(mock(Opponent.class));
		}
	}

}
