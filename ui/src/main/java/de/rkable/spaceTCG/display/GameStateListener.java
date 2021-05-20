package de.rkable.spaceTCG.display;

import de.rkable.spaceTCG.Game;

public interface GameStateListener {
	
	void startOver();

	void goBackToMap();
	
	void newGame(Game game);
}
