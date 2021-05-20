package de.rkable.spaceTCG;

import de.rkable.spaceTCG.display.ShipDisplayBuilder;
import de.rkable.spaceTCG.gameStats.change.ShipDamage;

public class Ship {
	
	private int shield = 0;
	private int maxShield = 0;
	private int maxHull;
	private int hull;
	
	public Ship(int shield, int hull) {
		this.hull = hull;
		this.maxHull = hull;
		this.shield = shield;
		this.maxShield = shield;
	}

	/**
	 * @param hull the current and the maximum hull
	 */
	public Ship(int hull) {
		this.hull = hull;
		maxHull = hull;
	}

	public boolean isAlive() {
		return hull > 0;
	}

	public void display(ShipDisplayBuilder builder) {
		builder.setHull(hull);
		builder.setMaxHull(maxHull);
		builder.setShield(shield);
		builder.setMaxShield(maxShield);
	}

	public void process(GameStateChange stateChange) {
		if (stateChange instanceof ShipDamage) {
			ShipDamage damage = (ShipDamage) stateChange;
			
			if (shield > 0 && damage.getShieldDamage() >= shield) {
				double amountRemaining = 1 - ((double) shield / damage.getShieldDamage());
				shield = 0;
				hull -= Math.round(amountRemaining * damage.getHullDamage());
			} else {
				shield -= damage.getShieldDamage();
				shield = Math.max(shield, 0);
				if (shield == 0) {
					hull -= damage.getHullDamage();
				}
			}
		}
	}
	
	int getHull() {
		return hull;
	}

	public int getMaxHull() {
		return maxHull;
	}
	
	public int getShield() {
		return shield;
	}
	
	public int getMaxShield() {
		return maxShield;
	}

}
