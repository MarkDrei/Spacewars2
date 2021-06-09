package de.rkable.spaceTCG.card;

import java.util.ArrayList;
import java.util.List;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public class BurstLaser implements Card {

	public static BurstLaser createTier1() {
		return new BurstLaser("Burst Laser Mk1", 1, 3, 3);
	}
	
	public static BurstLaser createTier2() {
		return new BurstLaser("Burst Laser Mk2", 1, 3, 4);
	}
	
	public static BurstLaser createTier3() {
		return new BurstLaser("Burst Laser Mk3", 1, 3, 5);
	}
	
	private int hullDamage = 1;
	private int shieldDamage = 3;
	private int burstSize = 3;
	private String name;
	
	private BurstLaser(String name, int hullDamage, int shieldDamage, int burstSize) {
		this.name = name;
		this.hullDamage = hullDamage;
		this.shieldDamage = shieldDamage;
		this.burstSize = burstSize;
	}
	
	@Override
	public String getName() {
		return name;
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
