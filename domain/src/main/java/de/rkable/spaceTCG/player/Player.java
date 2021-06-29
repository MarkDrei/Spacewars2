package de.rkable.spaceTCG.player;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.Ship;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToPlayer;
import de.rkable.spaceTCG.gameStats.change.RechargePlayerShield;

public class Player {

	private Ship ship;
	private PlayerDeck playerDeck;

	public Player(Ship ship, PlayerDeck gameDeck) {
		this.ship = ship;
		this.playerDeck = gameDeck;
	}

	public void process(GameStateChange gameStateChange) {
		if (gameStateChange instanceof DamageAppliedToPlayer) {
			ship.process(gameStateChange);
		} else if (gameStateChange instanceof RechargePlayerShield) {
			ship.process(gameStateChange); 
		}
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public boolean isAlive() {
		return ship.isAlive();
	}

	public PlayerDeck getDeck() {
		return playerDeck;
	}

}
