package de.rkable.spaceTCG.map;

import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.Opponent;
import de.rkable.spaceTCG.map.Waypoint.VisitAction;

public class VisitOpponent implements VisitAction {
	
	private Game game;
	private Opponent opponent;

	public VisitOpponent(Game game, Opponent opponent) {
		this.game = game;
		this.opponent = opponent;
	}

	@Override
	public void triggerVisit() {
		game.fightStarted(opponent);
	}

}
