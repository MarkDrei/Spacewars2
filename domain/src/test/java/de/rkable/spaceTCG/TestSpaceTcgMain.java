package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestSpaceTcgMain {

	@Test
	public void getTestFight() {
		Fight fight = SpaceTcgMain.getTestFight();
		assertNotNull(fight);
		assertNotNull(fight.display().deckDisplay.card1);
	}

	@Test
	public void getTestGame() {
		Game game = SpaceTcgMain.getTestGame();
		assertNotNull(game);

	}

}
