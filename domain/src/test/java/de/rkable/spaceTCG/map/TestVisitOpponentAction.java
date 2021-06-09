package de.rkable.spaceTCG.map;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.Opponent;

import static org.mockito.Mockito.*;

public class TestVisitOpponentAction {
	
	@Test
	public void triggerVisit_initiatesFight() {
		Game game = mock(Game.class);
		Opponent opponent = mock(Opponent.class);
		
		VisitOpponentAction visitOpponent = new VisitOpponentAction(game, opponent);
		visitOpponent.triggerVisit();
		verify(game).fightStarted(opponent);
	}

}
