package de.rkable.spaceTCG.gameStats.change;

import de.rkable.spaceTCG.GameStateChange;

public abstract class DamageAppliedToShip implements GameStateChange {
	
	private final ShipDamage shipDamage;

	public DamageAppliedToShip(int damage) {
		this.shipDamage = new ShipDamage(damage);
	}

	public ShipDamage getShipDamage() {
		return shipDamage;
	}

}
