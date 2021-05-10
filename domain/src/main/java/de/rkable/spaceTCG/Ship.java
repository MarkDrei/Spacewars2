package de.rkable.spaceTCG;

import de.rkable.spaceTCG.display.ShipDisplayBuilder;
import de.rkable.spaceTCG.gameStats.change.ShipDamage;

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

	public void process(GameStateChange stateChange) {
		if (stateChange instanceof ShipDamage) {
			hull -= ((ShipDamage) stateChange).getDamage();
		}
	}
	
	int getHull() {
		return hull;
	}

}
