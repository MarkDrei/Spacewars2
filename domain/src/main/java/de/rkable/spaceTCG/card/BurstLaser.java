package de.rkable.spaceTCG.card;

import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public class BurstLaser implements Card {

	private int baseDamage = 1;

	@Override
	public String getName() {
		return "Burst Laser";
	}

	@Override
	public List<GameStateChange> play(GameStats gameStats) {
		return Arrays.asList(
				new DamageAppliedToOpponent(baseDamage),
				new DamageAppliedToOpponent(baseDamage),
				new DamageAppliedToOpponent(baseDamage));
	}

}
