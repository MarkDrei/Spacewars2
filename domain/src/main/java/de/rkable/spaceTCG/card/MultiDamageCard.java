package de.rkable.spaceTCG.card;

import java.util.ArrayList;
import java.util.List;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public abstract class MultiDamageCard extends DamageCard {

	protected int burstSize;

	protected MultiDamageCard(String name, int hullDamage, int shieldDamage, int burstSize) {
		super(name, shieldDamage, hullDamage);
		this.burstSize = burstSize;
	}

	@Override
	public List<GameStateChange> play(GameStats gameStats) {
		List<GameStateChange> burstEffects = new ArrayList<>();
		for (int i = 0; i < burstSize; i++) {
			burstEffects.add(new DamageAppliedToOpponent(shieldDamage, hullDamage));
		}
		return burstEffects;
	}

}