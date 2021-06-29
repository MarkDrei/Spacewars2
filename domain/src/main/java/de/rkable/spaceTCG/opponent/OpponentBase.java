package de.rkable.spaceTCG.opponent;

import java.util.List;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.Opponent;
import de.rkable.spaceTCG.Ship;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public abstract class OpponentBase implements Opponent {
	
	private Ship ship;

	public OpponentBase(Ship ship) {
		this.ship = ship;
	}
	
	@Override
	public Ship getShip() {
		return ship;
	}

	@Override
	public abstract List<GameStateChange> performNextAction(GameStats gameStats);

	@Override
	public void process(GameStateChange stateChange) {
		if (stateChange instanceof DamageAppliedToOpponent) {
			ship.process(stateChange);
		}

	}

	@Override
	public boolean isAlive() {
		return ship.isAlive();
	}

}
