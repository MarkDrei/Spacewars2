package de.rkable.spaceTCG;

import java.util.List;

public interface Card {

	String getName();
	
	/**
	 * This card is played. Given the current state of the game, calculate the changes
	 * that should be applied to the game
	 * 
	 * @param gameStats The current state of the game
	 * @return a ordered list of changes to the game
	 */
	public List<GameStatChange> play(GameStats gameStats);
}
