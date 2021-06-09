package de.rkable.spaceTCG.card;

import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.Card.CardFactory;

/**
 * Provides access to all available cards
 *
 */
public class CardLibrary {
	
	private static CardFactory[] TIER_1 = new CardFactory[] {
		() -> {return BurstLaser.createTier1();},
		() -> {return Laser.createTier1();}
	};
	
	private static CardFactory[] TIER_2 = new CardFactory[] {
			() -> {return BurstLaser.createTier2();},
			() -> {return Laser.createTier2();}
	};
	
	private static CardFactory[] TIER_3 = new CardFactory[] {
			() -> {return BurstLaser.createTier3();},
			() -> {return Laser.createTier3();}
	};
	
	public List<CardFactory> getTier1Cards() {
		return Arrays.asList(TIER_1);
	}

	public List<CardFactory> getTier2Cards() {
		return Arrays.asList(TIER_2);
	}

	public List<CardFactory> getTier3Cards() {
		return Arrays.asList(TIER_3);
	}
}
