package de.rkable.spaceTCG.reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.Card.CardSupplier;
import de.rkable.spaceTCG.IRewardProvider;
import de.rkable.spaceTCG.card.CardLibrary;

public class RandomCardsReward implements IRewardProvider {
	
	private final static int NUMBER_OF_CARDS = 3;

	private Random random;

	private CardLibrary library;

	public RandomCardsReward(Random random, CardLibrary library) {
		this.random = random;
		this.library = library;
	}

	@Override
	public List<Card> getRewardsToChoseFrom() {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_CARDS; ++i) {
			cards.add(chooseCard());
		}
		
		return cards;
	}

	private Card chooseCard() {
		int nextInt = random.nextInt(100);
		
		if (nextInt > 97) {
			return getRandomCard(library.getTier3Cards());
		}
		if (nextInt > 85) {
			return getRandomCard(library.getTier2Cards());
		}
		
		return getRandomCard(library.getTier1Cards());
	}

	private Card getRandomCard(List<CardSupplier> cardsToChooseFrom) {
		return cardsToChooseFrom.get(random.nextInt(cardsToChooseFrom.size())).createCard();
	}

}
