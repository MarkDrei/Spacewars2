package de.rkable.spaceTCG.gameStats.change;

import de.rkable.spaceTCG.GameStateChange;

public abstract class DamageAppliedToShip implements GameStateChange {
	
	private int shieldDamage;
	private int hullDamage;
	
	public int getShieldDamage() {
		return shieldDamage;
	}
	
	public int getHullDamage() {
		return hullDamage;
	}

	public DamageAppliedToShip(int shieldDamage, int hullDamage) {
		this.shieldDamage = shieldDamage;
		this.hullDamage = hullDamage;
	}

}
