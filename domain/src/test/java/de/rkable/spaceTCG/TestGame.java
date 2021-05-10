package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.map.VisitOpponent;
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
	
	@Test
	public void addGameListener() {
		game.addGameListener(new GameListener() {
			@Override
			public void fightInitiated(Fight fight) {
				// nop
			}
		});
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
	
	@SuppressWarnings("boxing")
	@Test
	public void visit_whenWaypointIsNotReachable_throws() {
		when(worldMap.isReachable(any())).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> game.visit(mock(Waypoint.class)));
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
		public void visit_travelsOnMap() {
			game.setWorldMap(worldMap);
			when(worldMap.isReachable(any())).thenReturn(true);
			
			Waypoint waypoint = mock(Waypoint.class);
			game.visit(waypoint);
			
			verify(worldMap).travel(waypoint);
		}
		
		/**
		 * This is more a component test style
		 */
		@Test
		public void visit_whenWaypointHasFight_initiatesTheFight() {
			Waypoint waypointMock = new Waypoint(new VisitOpponent(game, mock(Opponent.class)));
			Waypoint start = new Waypoint(waypointMock);
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
		
		@Test
		public void fightStarted_afterPreviousFightHasFinished_works() {
			game.fightStarted(mock(Opponent.class));
			game.victory();
			game.fightStarted(mock(Opponent.class));
		}
	}

}
