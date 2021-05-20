package de.rkable.spaceTCG.display;

import de.rkable.spaceTCG.display.FightDisplay.ShipDisplay;

public class ShipDisplayBuilder {
	private int hull;
	private int maxHull;
	private int shield;
	private int maxShield;
	
	public void setHull(int hull) {
		this.hull = hull;
	}
	
	public void setMaxHull(int maxHull) {
		this.maxHull = maxHull;
	}
	
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public void setMaxShield(int maxShield) {
		this.maxShield = maxShield;
	}
	
	public ShipDisplay build() {
		return new ShipDisplay(shield, maxShield, hull, maxHull);
	}
}