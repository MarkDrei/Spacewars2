package de.rkable.spaceTCG.display;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.card.BasicLaser;

public class TestFightDisplayBuilder {
	
	@Test
	public void build_returnsFightDisplay() {
		FightDisplayBuilder builder = new FightDisplayBuilder();
		FightDisplay display = builder.build();
		assertNotNull(display);
	}
	
	@Test
	public void player_givesPlayersShipBuilder() {
		FightDisplayBuilder builder = new FightDisplayBuilder();
		builder.player().setHull(42);
		FightDisplay display = builder.build();
		assertEquals(42, display.player.hull);
	}
	
	@Test
	public void opponent_givesOpponentShipBuilder() {
		FightDisplayBuilder builder = new FightDisplayBuilder();
		builder.opponent().setHull(23);
		FightDisplay display = builder.build();
		assertEquals(23, display.opponent.hull);
	}

	@Test
	public void sdf() {
		FightDisplayBuilder builder = new FightDisplayBuilder();
		builder.setDeckDisplay(new DeckDisplay(new BasicLaser(), new BasicLaser(), new BasicLaser(), new BasicLaser()));
		FightDisplay display = builder.build();
		assertEquals("Laser", display.deckDisplay.card1.getName());
		assertEquals("Laser", display.deckDisplay.card2.getName());
		assertEquals("Laser", display.deckDisplay.card3.getName());
		assertEquals("Laser", display.deckDisplay.card4.getName());
	}


}
