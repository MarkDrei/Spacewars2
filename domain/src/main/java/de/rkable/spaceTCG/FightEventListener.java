package de.rkable.spaceTCG;

import java.util.List;

public interface FightEventListener {

	/**
	 * Event when a card is played
	 * 
	 * @param card    The card that was played
	 * @param changes a list of changes that happen due to the played card
	 */
	default void cardPlayed(Card card, List<GameStateChange> changes) {
		// default to do nothing
	}

	/**
	 * Event when the player wins the battle
	 * @param rewardOptions 
	 */
	default void victory(List<Card> rewardOptions) {
		// default to do nothing
	}

	/**
	 * Event when the player loses the battle
	 */
	default void defeat() {
		// default to do nothing
	}

	/**
	 * A turn of by the opponent with changes to the game state
	 * 
	 * @param changes a list of changes that happen due to the opponent turn
	 */
	default void opponentPlayed(List<GameStateChange> changes) {
		// default to do nothing
	}
}
