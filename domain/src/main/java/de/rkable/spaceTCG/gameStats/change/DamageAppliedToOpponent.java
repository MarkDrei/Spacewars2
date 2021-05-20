package de.rkable.spaceTCG.gameStats.change;

/**
 * Describes that damage was applied to the opponent
 */
public class DamageAppliedToOpponent extends DamageAppliedToShip {
	
	public DamageAppliedToOpponent(int shieldDamage, int hullDamage) {
		super(shieldDamage, hullDamage);
	}

}
