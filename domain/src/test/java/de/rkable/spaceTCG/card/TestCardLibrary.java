package de.rkable.spaceTCG.card;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.Card.CardSupplier;

public class TestCardLibrary {
	
	private final static int NUM_TIER1_CARDS = 4;
	private final static int NUM_TIER2_CARDS = 4;
	private final static int NUM_TIER3_CARDS = 4;

	@Test
	public void getTier1Cards_givesAllTier1Cards() {
		CardLibrary cardLibrary = new CardLibrary();
		List<CardSupplier> commonCards = cardLibrary.getTier1Cards();
		assertEquals(NUM_TIER1_CARDS, commonCards.size());
	}
	
	@Test
	public void getTier2Cards_givesAllTier2Cards() {
		CardLibrary cardLibrary = new CardLibrary();
		List<CardSupplier> commonCards = cardLibrary.getTier2Cards();
		assertEquals(NUM_TIER2_CARDS, commonCards.size());
	}
	
	@Test
	public void getTier3Cards_givesAllTier3Cards() {
		CardLibrary cardLibrary = new CardLibrary();
		List<CardSupplier> commonCards = cardLibrary.getTier3Cards();
		assertEquals(NUM_TIER3_CARDS, commonCards.size());
	}
}
