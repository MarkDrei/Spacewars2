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

	public static Game getTestGame() {
		Game game = new Game(getDefaultPlayer(), (player2, opponent) -> {return new Fight(player2, opponent, reward);});
		
		Waypoint goal2 = new Waypoint("Goal 2", new VisitOpponentAction(game, new NeutralOpponent1()));
		Waypoint waypoint2_4 = new Waypoint("Waypoint 4", new VisitOpponentAction(game, new NeutralOpponent1()), goal2);
		Waypoint waypoint2_3 = new Waypoint("Waypoint 3", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint2_4);
		Waypoint waypoint2_2 = new Waypoint("Waypoint 2", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint2_3);
		Waypoint waypoint2_1 = new Waypoint("Waypoint 1", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint2_2);
		Waypoint start2 = new Waypoint("Start 2", new VisitOpponentAction(game, new NeutralOpponent1()), waypoint2_1);
		WorldMap map2 = new WorldMap(start2);
		
		Waypoint goal1 = new Waypoint("Goal 1", new MoveToNextMapAction(game, new NeutralOpponent1(), map2));
		Waypoint diamond1 = new Waypoint("Waypoint 1_1", new VisitOpponentAction(game, new NeutralOpponent1()), goal1);
		Waypoint diamond2 = new Waypoint("Waypoint 1_2", new VisitOpponentAction(game, new NeutralOpponent1()), goal1);
		Waypoint start = new Waypoint("Start 1", new VisitOpponentAction(game, new NeutralOpponent1()), diamond1, diamond2);
		
		WorldMap map1 = new WorldMap(start);
		game.setWorldMap(map1);
		
		return game;
	}
	
	private static Player getDefaultPlayer() {
		PlayerDeck gameDeck = new PlayerDeck(
				// 7x
				Laser.createTier1(),
				Laser.createTier1(),
				Laser.createTier1(),
				Laser.createTier1(),
				Laser.createTier1(),
				Laser.createTier1(),
				Laser.createTier1(),
				// 3x
				BurstLaser.createTier1(),
				BurstLaser.createTier1(),
				BurstLaser.createTier1());
		
		Ship playerShip = new Ship(20, 50);
		return new Player(playerShip, gameDeck);
	}
	

}
