package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.display.FightDisplay.ShipDisplay;
import de.rkable.spaceTCG.display.ShipDisplayBuilder;

public class TestShip {
	
	@Test
	public void isAlive_withPositiveHull_isTrue() {
		Ship ship = new Ship(1);
		assertTrue(ship.isAlive());
	}
	
	@Test
	public void isAlive_withZeroHull_isFalse() {
		Ship ship = new Ship(0);
		assertFalse(ship.isAlive());
		ship = new Ship(-1);
		assertFalse(ship.isAlive());
	}
	
	@Test
	public void display_fillsInData() {
		ShipDisplayBuilder builder = new ShipDisplayBuilder();
		new Ship(42).display(builder);
		ShipDisplay ship = builder.build();
		assertEquals(42, ship.hull);
	}

}
