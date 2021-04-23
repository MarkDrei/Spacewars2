package de.rkable.spaceTCG.display;

import de.rkable.spaceTCG.display.FightDisplay.ShipDisplay;

public class ShipDisplayBuilder {
	private int hull;
	
	public void setHull(int hull) {
		this.hull = hull;
	}
	
	public ShipDisplay build() {
		return new ShipDisplay(hull);
	}
}