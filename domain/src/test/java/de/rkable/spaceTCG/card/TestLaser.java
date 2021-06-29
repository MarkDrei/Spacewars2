package de.rkable.spaceTCG.card;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.StringContains.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.display.FightDisplayBuilder;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public class TestLaser {
	
	private Card laserMk1 = Laser.FACTORY.createTier1();
	private Card laserMk2 = Laser.FACTORY.createTier2();
	private Card laserMk3 = Laser.FACTORY.createTier3();
	
	@Test
	public void name() {
		assertEquals("Laser Mk1", laserMk1.getName());
		assertEquals("Laser Mk2", laserMk2.getName());
		assertEquals("Laser Mk3", laserMk3.getName());
	}
	
	@Test
	public void play_deductsRightAmountOfOpponentShipHull() {
		// prepare
		GameStats gameStats = mock(GameStats.class);
		FightDisplayBuilder fightDisplayBuilder = new FightDisplayBuilder();
		fightDisplayBuilder.opponent().setHull(5);
		when(gameStats.getFightDisplay()).thenReturn(fightDisplayBuilder.build());
		
		// execute
		List<GameStateChange> changes = laserMk1.play(gameStats);
		
		// verify
		assertEquals(1, changes.size());
		GameStateChange change1 = changes.get(0);
		DamageAppliedToOpponent damage = (DamageAppliedToOpponent) change1;
		assertEquals(2, damage.getHullDamage());
	}
	
	@Test
	public void getDescription_laserMk1() {
		String description = laserMk1.getDescription();
		assertThat(description, containsString("Laser Mk1"));
		assertThat(description, containsString("2 damage to the hull"));
		assertThat(description, containsString("8 damage to shields"));
	}
	
	@Test
	public void getDescription_laserMk2() {
		String description = laserMk2.getDescription();
		assertThat(description, containsString("Laser Mk2"));
		assertThat(description, containsString("3 damage to the hull"));
		assertThat(description, containsString("10 damage to shields"));
	}
	
	@Test
	public void getDescription_laserMk3() {
		String description = laserMk3.getDescription();
		assertThat(description, containsString("Laser Mk3"));
		assertThat(description, containsString("4 damage to the hull"));
		assertThat(description, containsString("12 damage to shields"));
	}

}
