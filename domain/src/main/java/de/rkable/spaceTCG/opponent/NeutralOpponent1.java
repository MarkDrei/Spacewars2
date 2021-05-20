package de.rkable.spaceTCG.opponent;

import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.Ship;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToPlayer;

public class NeutralOpponent1 extends OpponentBase {
	
	public NeutralOpponent1() {
		super(new Ship(10, 10));
	}

	@Override
	public List<GameStateChange> performNextAction(GameStats gameStats) {
		return Arrays.asList(new DamageAppliedToPlayer(5, 2));
	}

}
