package de.rkable.spaceTCG;

import java.util.List;

public interface Opponent {
	
	Ship getShip();
	
	List<GameStateChange> performNextAction(GameStats gameStats);

	void process(GameStateChange stateChange);

	boolean isAlive();

}
