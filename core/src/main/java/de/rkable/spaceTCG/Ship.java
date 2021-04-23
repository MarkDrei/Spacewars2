package de.rkable.spaceTCG;

import de.rkable.spaceTCG.display.ShipDisplayBuilder;

public class Ship {
	
	private int hull;

	public Ship(int hull) {
		this.hull = hull;
	}

	public boolean isAlive() {
		return hull > 0;
	}

	public void display(ShipDisplayBuilder builder) {
		builder.setHull(hull);
	}

}
