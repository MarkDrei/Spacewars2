package de.rkable.spaceTCG;

import de.rkable.spaceTCG.card.Laser;

import java.util.Random;

import de.rkable.spaceTCG.card.BurstLaser;
import de.rkable.spaceTCG.card.CardLibrary;
import de.rkable.spaceTCG.map.MoveToNextMapAction;
import de.rkable.spaceTCG.map.VisitOpponentAction;
import de.rkable.spaceTCG.map.Waypoint;
import de.rkable.spaceTCG.map.WorldMap;
import de.rkable.spaceTCG.opponent.NeutralOpponent1;
import de.rkable.spaceTCG.player.Player;
import de.rkable.spaceTCG.player.PlayerDeck;
import de.rkable.spaceTCG.reward.RandomCardsReward;

public class SpaceTcgMain {
	
	static RandomCardsReward reward = new RandomCardsReward(new Random(), new CardLibrary());
	
	public static Fight getTestFight() {
		return new Fight(getDefaultPlayer(), new NeutralOpponent1(), reward);
	}
	
	public static Game getGame() {
		Game game = new Game(getDefaultPlayer(), getDefaultFightFactory());
		
		return game;
	}
	
	private static FightFactory getDefaultFightFactory() {
		return (player, opponent) -> {
			return new Fight(player, opponent, reward);
		};
	}

	public static Game getTestGame() {
		Game game = new Game(getDefaultPlayer(), getDefaultFightFactory());
		
		Waypoint goal2 = new Waypoint("Goal 2", new VisitOpponentAction(game, new NeutralOpponent1()));
		Waypoint waypoint2_4 = new Waypoint("Waypoint 4", new VisitOpponentAction(game, new NeutralOpponent1()), goal2);
		Waypoint waypoint2_3 = new Waypoint("Waypoint 3", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint2_4);
		Waypoint waypoint2_2 = new Waypoint("Waypoint 2", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint2_3);
		Waypoint waypoint2_1 = new Waypoint("Waypoint 1", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint2_2);
		Waypoint start2 = new Waypoint("Start 2", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint2_1);
		WorldMap map2 = new WorldMap(start2);
		
		Waypoint goal1 = new Waypoint("Goal 1", new MoveToNextMapAction(game, new NeutralOpponent1(), map2));
		
		Waypoint waypoint3_1 = new Waypoint("Waypoint 2_1", new VisitOpponentAction(game, new NeutralOpponent1()), goal1);
		Waypoint waypoint3_2 = new Waypoint("Waypoint 2_2", new VisitOpponentAction(game, new NeutralOpponent1()), goal1);
		Waypoint waypoint3_3 = new Waypoint("Waypoint 2_3", new VisitOpponentAction(game, new NeutralOpponent1()), goal1);
		
		Waypoint waypoint1_2 = new Waypoint("Waypoint 2", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint3_3);
		Waypoint waypoint1_1 = new Waypoint("Waypoint 1", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint3_1, waypoint3_2);
		
		Waypoint start = new Waypoint("Start 1", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint1_1, waypoint1_2);
		
		WorldMap map1 = new WorldMap(start);
		game.setWorldMap(map1);
		
		return game;
	}
	
	private static Player getDefaultPlayer() {
		PlayerDeck gameDeck = new PlayerDeck(
				// 7x
				Laser.FACTORY.createTier1(),
				Laser.FACTORY.createTier1(),
				Laser.FACTORY.createTier1(),
				Laser.FACTORY.createTier1(),
				Laser.FACTORY.createTier1(),
				Laser.FACTORY.createTier1(),
				Laser.FACTORY.createTier1(),
				// 3x
				BurstLaser.FACTORY.createTier1(),
				BurstLaser.FACTORY.createTier1(),
				BurstLaser.FACTORY.createTier1());
		
		Ship playerShip = new Ship(20, 50);
		return new Player(playerShip, gameDeck);
	}
	

}
