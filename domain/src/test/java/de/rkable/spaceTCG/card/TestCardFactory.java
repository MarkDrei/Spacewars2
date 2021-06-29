package de.rkable.spaceTCG.card;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;

public class TestCardFactory {
	
	class MyCard implements Card {

		@Override
		public String getName() {
			return null;
		}

		@Override
		public String getDescription() {
			return null;
		}

		@Override
		public List<GameStateChange> play(GameStats gameStats) {
			return null;
		}
		
	}
	
	@Test
	public void factory_withOnly1Supplier_givesOnlyTier1() {
		CardFactory cardFactory = new CardFactory(MyCard::new);
		assertTrue(cardFactory.hasTier1());
		assertTrue(cardFactory.createTier1() instanceof MyCard);
		assertNotNull(cardFactory.getTier1Supplier());
		assertFalse(cardFactory.hasTier2());
		assertThrows(RuntimeException.class, () -> cardFactory.createTier2());
		assertThrows(RuntimeException.class, () -> cardFactory.getTier2Supplier());
	}
	
	@Test
	public void factory_createsUniqueCards() {
		CardFactory cardFactory = new CardFactory(MyCard::new);
		Card firstCard = cardFactory.createTier1();
		Card secondCard = cardFactory.createTier1();
		assertNotSame(firstCard, secondCard);
	}
	
	
	@Test
	public void factory_withTier1_2_3Suppliers_givesCardsForAllTiers() {
		CardFactory cardFactory = new CardFactory(MyCard::new, MyCard::new, MyCard::new);
		
		assertTrue(cardFactory.hasTier1());
		assertTrue(cardFactory.createTier1() instanceof MyCard);
		assertNotNull(cardFactory.getTier1Supplier());
		
		assertTrue(cardFactory.hasTier2());
		assertTrue(cardFactory.createTier2() instanceof MyCard);
		assertNotNull(cardFactory.getTier2Supplier());
		
		assertTrue(cardFactory.hasTier3());
		assertTrue(cardFactory.createTier3() instanceof MyCard);
		assertNotNull(cardFactory.getTier3Supplier());
	}
	
	@Test
	public void factory_withTier1and3onlySuppliers_givesNoTier2() {
		CardFactory cardFactory = new CardFactory(MyCard::new, null, MyCard::new);
		assertTrue(cardFactory.hasTier1());
		assertTrue(cardFactory.createTier1() instanceof MyCard);
		assertNotNull(cardFactory.getTier1Supplier());
		
		assertFalse(cardFactory.hasTier2());
		assertThrows(RuntimeException.class, () -> cardFactory.createTier2());
		assertThrows(RuntimeException.class, () -> cardFactory.getTier2Supplier());
		
		assertTrue(cardFactory.hasTier3());
		assertTrue(cardFactory.createTier3() instanceof MyCard);
		assertNotNull(cardFactory.getTier3Supplier());
	}
	
	@Test
	public void constructor_withMoreSuppliersThanTiers_throws() {
		assertThrows(RuntimeException.class, () -> new CardFactory(MyCard::new, MyCard::new, MyCard::new, MyCard::new));
	}

}
