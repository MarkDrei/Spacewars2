package de.rkable.spaceTCG;

import java.util.List;

public interface Card {
	
	interface CardFactory {
		Card createCard();
	}

	/**
	 * The name of the card to be displayed
	 * @return
	 */
	String getName();
	
	String getDescription();
	
	/**
	 * This card is played. Given the current state of the game, calculate the changes
	 * that should be applied to the game
	 * 
	 * @param gameStats The current state of the game
	 * @return a ordered list of changes to the game
	 */
	public List<GameStateChange> play(GameStats gameStats);
}
