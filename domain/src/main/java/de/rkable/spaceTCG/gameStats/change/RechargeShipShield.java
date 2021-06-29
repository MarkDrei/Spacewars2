package de.rkable.spaceTCG.gameStats.change;

import de.rkable.spaceTCG.GameStateChange;

public abstract class RechargeShipShield implements GameStateChange {
	
	private int amountToRecharge;

	public RechargeShipShield(int amountToRecharge) {
		this.amountToRecharge = amountToRecharge;
	}
	
	public int getAmountToRecharge() {
		return amountToRecharge;
	}

}
