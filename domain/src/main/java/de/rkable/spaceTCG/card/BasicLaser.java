package de.rkable.spaceTCG.card;

import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public class BasicLaser implements Card {
	
	private String name = "Laser";
	private int baseDamage = 3;
	
	public BasicLaser() {
		this("Laser", 3);
	}
	
	public BasicLaser(String name, int baseDamage) {
		this.name = name;
		this.baseDamage = baseDamage;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<GameStateChange> play(GameStats gameStats) {
		return Arrays.asList(new DamageAppliedToOpponent(baseDamage));
	}

}
