package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.display.FightDisplay.ShipDisplay;
import de.rkable.spaceTCG.display.ShipDisplayBuilder;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToPlayer;
import de.rkable.spaceTCG.gameStats.change.RechargePlayerShield;

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
		new Ship(42, 23).display(builder);
		ShipDisplay ship = builder.build();
		assertEquals(42, ship.maxShield);
		assertEquals(42, ship.shield);
		assertEquals(23, ship.maxHull);
		assertEquals(23, ship.hull);
	}
	
	@Test
	public void process_hullDamageWithNoShield_deductsFromHull() {
		Ship ship = new Ship(1);
		ship.process(new DamageAppliedToPlayer(0, 3));
		assertEquals(1, ship.getMaxHull());
		assertEquals(-2, ship.getHull());
	}
	
	@Test
	public void process_shieldDamageWithShield_deductsFromShield() {
		Ship ship = new Ship(10, 10);
		ship.process(new DamageAppliedToPlayer(5, 0));
		assertEquals(10, ship.getMaxShield());
		assertEquals(5, ship.getShield());
		ship.process(new DamageAppliedToPlayer(5, 0));
		assertEquals(0, ship.getShield());
		ship.process(new DamageAppliedToPlayer(5, 0));
		assertEquals(0, ship.getShield());
	}
	
	@Test
	public void process_hullDamageWhenShipHasShield_deductsNothing() {
		Ship ship = new Ship(10, 10);
		ship.process(new DamageAppliedToPlayer(0, 5));
		assertEquals(10, ship.getHull());
		assertEquals(10, ship.getShield());
	}
	
	@Test
	public void process_bothDamageTypesPartially_deductsPartially() {
		Ship ship = new Ship(5, 10);
		ship.process(new DamageAppliedToPlayer(10, 8));
		assertEquals(0, ship.getShield());
		assertEquals(6, ship.getHull());
	}
	
	@Test
	public void process_bothDamageTypesPartially_deductsPartially2() {
		Ship ship = new Ship(5, 10);
		ship.process(new DamageAppliedToPlayer(7, 7));
		assertEquals(0, ship.getShield());
		assertEquals(8, ship.getHull());
	}
	
	@Test
	public void process_rechargeShield_rechargesShield() {
		Ship ship = new Ship(10, 0);
		ship.process(new DamageAppliedToPlayer(5, 0));
		assertEquals(5, ship.getShield());
		
		ship.process(new RechargePlayerShield(3));
		assertEquals(8, ship.getShield());
	}
	
	@Test
	public void process_rechargeShield_doesNotOvercharge() {
		Ship ship = new Ship(10, 0);
		ship.process(new DamageAppliedToPlayer(5, 0));
		assertEquals(5, ship.getShield());
		
		ship.process(new RechargePlayerShield(8));
		assertEquals(10, ship.getShield());
	}

}
