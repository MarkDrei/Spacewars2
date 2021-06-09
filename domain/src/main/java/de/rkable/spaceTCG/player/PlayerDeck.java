package de.rkable.spaceTCG.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.Card;

public class PlayerDeck {
	
	private List<Card> cards = new ArrayList<>();
	
	public PlayerDeck(Card... cards) {
		this.cards.addAll(Arrays.asList(cards));
	}

	public int getDeckSize() {
		return cards.size();
	}

	public List<Card> getAllCards() {
		return new ArrayList<>(cards);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
}
