package de.rkable.spaceTCG.gameStats.change;

import de.rkable.spaceTCG.GameStateChange;

/**
 * Change: Damage was applied to a ship
 */
public class ShipDamage implements GameStateChange {

	private int damage;

	public ShipDamage(int damage) {
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
}
