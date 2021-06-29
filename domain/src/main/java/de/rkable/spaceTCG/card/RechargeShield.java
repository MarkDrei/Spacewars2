package de.rkable.spaceTCG.card;

import java.util.List;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;

public class RechargeShield implements Card {
	
	
	
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return "Recharges the shield by 8 points";
	}

	@Override
	public List<GameStateChange> play(GameStats gameStats) {
		// TODO Auto-generated method stub
		return null;
	}

}
