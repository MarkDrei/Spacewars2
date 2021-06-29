package de.rkable.spaceTCG.card;

import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public abstract class SingleDamageCard extends DamageCard {

	protected SingleDamageCard(String name, int shieldDamage, int hullDamage) {
		super(name, shieldDamage, hullDamage);
	}

	@Override
	public List<GameStateChange> play(GameStats gameStats) {
		return Arrays.asList(new DamageAppliedToOpponent(shieldDamage, hullDamage));
	}

}