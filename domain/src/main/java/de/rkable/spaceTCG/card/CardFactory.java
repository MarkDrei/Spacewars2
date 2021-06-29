package de.rkable.spaceTCG.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.Card.CardSupplier;

public class CardFactory {

	private final static int MAX_TIER = 3;

	private List<Optional<Card.CardSupplier>> suppliers = new ArrayList<>();

	@SafeVarargs
	public CardFactory(Card.CardSupplier... supplierArray) {
		if (supplierArray.length > MAX_TIER) {
			throw new RuntimeException("Implementation Eror: more suppliers than tiers provided");
		}
		for (int tier = 1; tier <= MAX_TIER; tier++) {
			if (supplierArray.length >= tier && supplierArray[tier - 1] != null) {
				suppliers.add(Optional.of(supplierArray[tier - 1]));
			} else {
				suppliers.add(Optional.empty());
			}
		}
	}

	public Card createTier1() {
		return createCard(suppliers.get(0));
	}

	public Card createTier2() {
		return createCard(suppliers.get(1));
	}
	
	public Card createTier3() {
		return createCard(suppliers.get(2));
	}
	
	private Card createCard(Optional<Card.CardSupplier> optionalSupplier) {
		return getSupplier(optionalSupplier).createCard();
	}

	public boolean hasTier1() {
		return suppliers.get(0).isPresent();
	}

	public boolean hasTier2() {
		return suppliers.get(1).isPresent();
	}
	
	public boolean hasTier3() {
		return suppliers.get(2).isPresent();
	}

	public CardSupplier getTier1Supplier() {
		return getSupplier(suppliers.get(0));
	}
	
	public CardSupplier getTier2Supplier() {
		return getSupplier(suppliers.get(1));
	}
	public CardSupplier getTier3Supplier() {
		return getSupplier(suppliers.get(2));
	}
	
	private CardSupplier getSupplier(Optional<Card.CardSupplier> optionalSupplier) {
		if (!optionalSupplier.isPresent()) {
			throw new RuntimeException("Implementation Error: There is no tier 1 version of this card!");
		}
		return optionalSupplier.get();
	}


}
