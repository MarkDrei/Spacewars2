package de.rkable.spaceTCG.card;

import java.util.ArrayList;
import java.util.List;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public class BurstLaser implements Card {

	private int hullDamage = 1;
	private int shieldDamage = 3;
	private int burstSize = 3;

	@Override
	public String getName() {
		return "Burst Laser";
	}

	@Override
	public List<GameStateChange> play(GameStats gameStats) {
		List<GameStateChange> burstEffects = new ArrayList<>();
		for (int i = 0; i < burstSize; i++) {
			burstEffects.add(new DamageAppliedToOpponent(shieldDamage, hullDamage));
		}
		return burstEffects;
	}
	
	@Override
	public String getDescription() {
		return "\"" + getName() + "\" fires 3 laser beams, each causing "
				+ shieldDamage + " damage to shields and "
				+ hullDamage + " damage to the hull.";
	}

}
