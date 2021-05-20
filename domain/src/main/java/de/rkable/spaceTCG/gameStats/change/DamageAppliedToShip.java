package de.rkable.spaceTCG.gameStats.change;

import de.rkable.spaceTCG.GameStateChange;

public abstract class DamageAppliedToShip implements GameStateChange {
	
	private final ShipDamage shipDamage;

	public DamageAppliedToShip(int shieldDamage, int hullDamage) {
		this.shipDamage = new ShipDamage(shieldDamage, hullDamage);
	}

	public ShipDamage getShipDamage() {
		return shipDamage;
	}

}
