package de.rkable.spaceTCG.card;

import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public class BasicLaser implements Card {
	
	private String name = "Laser";
	private int shieldDamage;
	private int hullDamage;
	
	public BasicLaser() {
		this("Laser", 10, 3);
	}
	
	public BasicLaser(String name, int shieldDamage, int hullDamage) {
		this.name = name;
		this.shieldDamage = shieldDamage;
		this.hullDamage = hullDamage;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<GameStateChange> play(GameStats gameStats) {
		return Arrays.asList(new DamageAppliedToOpponent(shieldDamage, hullDamage));
	}
	
	@Override
	public String getDescription() {
		return "\"" + getName() + "\" fires a laser beam, causing "
				+ shieldDamage + " damage to shields and "
				+ hullDamage + " damage to the hull.";
	}

}
