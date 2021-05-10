package de.rkable.spaceTCG.map;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.Opponent;

import static org.mockito.Mockito.*;

public class TestVisitOpponent {
	
	@Test
	public void test() {
		Game game = mock(Game.class);
		Opponent opponent = mock(Opponent.class);
		
		VisitOpponent visitOpponent = new VisitOpponent(game, opponent);
		visitOpponent.triggerVisit();
		verify(game).fightStarted(opponent);
	}

}
