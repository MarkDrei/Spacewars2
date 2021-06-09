package de.rkable.spaceTCG.reward;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.IRewardProvider;
import de.rkable.spaceTCG.card.CardLibrary;

public class TestRandomCardsProvider {
	
	private Random random = new Random(0);
	private IRewardProvider provider;
	
	@BeforeEach
	public void setup() {
		provider = new RandomCardsReward(random, new CardLibrary());
	}
	
	@Test
	public void getRewards_givesNonEmptyList() {
		List<Card> rewards = provider.getRewardsToChoseFrom();
		assertFalse(rewards.isEmpty());
	}
	
	@Test
	public void getRewards_gives3CardsToChooseFrom() {
		List<Card> rewards = provider.getRewardsToChoseFrom();
		assertEquals(3, rewards.size());
	}
	
	@Test
	public void getRewards_withRandom_givesExpectedDistribution() {
		CardLibrary cardLibrary = spy(new CardLibrary());
		
		provider = new RandomCardsReward(random, cardLibrary);
		for (int i = 0; i < 1000; ++i) {
			provider.getRewardsToChoseFrom();
		}
		// 3000 cards in total
		verify(cardLibrary, times(2580)).getTier1Cards();
		verify(cardLibrary, times(370)).getTier2Cards();
		verify(cardLibrary, times(50)).getTier3Cards();
	}

}
