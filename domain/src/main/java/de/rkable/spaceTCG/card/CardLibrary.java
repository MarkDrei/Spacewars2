package de.rkable.spaceTCG.card;

import java.util.ArrayList;
import java.util.List;

import de.rkable.spaceTCG.Card.CardSupplier;

/**
 * Provides access to all available cards
 *
 */
public class CardLibrary {
	
	private static CardFactory[] factories = new CardFactory[] {
			Laser.FACTORY,
			Cannon.FACTORY,
			BurstLaser.FACTORY,
			Minigun.FACTORY,
	};
	
	private List<CardSupplier> tier1Cards = new ArrayList<>();
	private List<CardSupplier> tier2Cards = new ArrayList<>();
	private List<CardSupplier> tier3Cards = new ArrayList<>();

	
	public CardLibrary() {
		for (CardFactory factory : factories) {
			if (factory.hasTier1()) {
				tier1Cards.add(factory.getTier1Supplier());
			}
			if (factory.hasTier2()) {
				tier2Cards.add(factory.getTier2Supplier());
			}
			if (factory.hasTier3()) {
				tier3Cards.add(factory.getTier3Supplier());
			}
		}
	}
	
	public List<CardSupplier> getTier1Cards() {
		return tier1Cards;
	}

	public List<CardSupplier> getTier2Cards() {
		return tier2Cards;
	}

	public List<CardSupplier> getTier3Cards() {
		return tier3Cards;
	}

}
