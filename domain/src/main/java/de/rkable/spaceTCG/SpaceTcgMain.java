package de.rkable.spaceTCG;

import de.rkable.spaceTCG.card.BasicLaser;
import de.rkable.spaceTCG.card.BurstLaser;
import de.rkable.spaceTCG.map.VisitOpponent;
import de.rkable.spaceTCG.map.Waypoint;
import de.rkable.spaceTCG.map.WorldMap;
import de.rkable.spaceTCG.opponent.NeutralOpponent1;
import de.rkable.spaceTCG.player.Player;
import de.rkable.spaceTCG.player.PlayerDeck;

public class SpaceTcgMain {
	
	public static Fight getTestFight() {
		return new Fight(getDefaultPlayer(), new NeutralOpponent1());
	}

	public static Game getTestGame() {
		Game game = new Game(getDefaultPlayer(), (player2, opponent) -> {return new Fight(player2, opponent);});
		
		Waypoint goal = new Waypoint(new VisitOpponent(game, new NeutralOpponent1()));
		Waypoint diamond1 = new Waypoint(new VisitOpponent(game, new NeutralOpponent1()), goal);
		Waypoint diamond2 = new Waypoint(new VisitOpponent(game, new NeutralOpponent1()), goal);
		Waypoint start = new Waypoint(new VisitOpponent(game, new NeutralOpponent1()), diamond1, diamond2);
		
		WorldMap map = new WorldMap(start);
		game.setWorldMap(map);
		
		return game;
	}
	
	private static Player getDefaultPlayer() {
		PlayerDeck gameDeck = new PlayerDeck(
				new BasicLaser("Laser 1", 10, 1),
				new BasicLaser("Laser 2", 10, 2),
				new BasicLaser("Laser 3", 10, 3),
				new BasicLaser("Laser 4", 10, 4),
				new BurstLaser());
		
		Ship playerShip = new Ship(20, 50);
		return new Player(playerShip, gameDeck);
	}
	

}
