package de.rkable.spaceTCG.card.gameStats.change;

import de.rkable.spaceTCG.GameStatChange;

/**
 * Describes that damage was applied to the opponent
 */
public class DamageAppliedToOpponent implements GameStatChange {
	
	private int remainingHull;
	private int damage;

	public DamageAppliedToOpponent(int damage, int remainingHull) {
		this.damage = damage;
		this.remainingHull = remainingHull;
	}

	public int getDamageValue() {
		return damage;
	}

	public int getRemainingOpponentHealth() {
		return remainingHull;
	}

}
