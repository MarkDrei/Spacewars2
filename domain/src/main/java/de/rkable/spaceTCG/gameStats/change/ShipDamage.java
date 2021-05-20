package de.rkable.spaceTCG.gameStats.change;

import de.rkable.spaceTCG.GameStateChange;

/**
 * Change: Damage was applied to a ship
 */
public class ShipDamage implements GameStateChange {

	private int shieldDamage;
	private int hullDamage;

	public ShipDamage(int shieldDamage, int hullDamage) {
		this.shieldDamage = shieldDamage;
		this.hullDamage = hullDamage;
	}
	
	public int getShieldDamage() {
		return shieldDamage;
	}
	
	public int getHullDamage() {
		return hullDamage;
	}
}
