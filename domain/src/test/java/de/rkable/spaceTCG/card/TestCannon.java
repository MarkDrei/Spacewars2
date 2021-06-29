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

public class TestCannon {
	
	private Card CannonMk1 = Cannon.FACTORY.createTier1();
	private Card CannonMk2 = Cannon.FACTORY.createTier2();
	private Card CannonMk3 = Cannon.FACTORY.createTier3();
	
	@Test
	public void name() {
		assertEquals("Cannon Mk1", CannonMk1.getName());
		assertEquals("Cannon Mk2", CannonMk2.getName());
		assertEquals("Cannon Mk3", CannonMk3.getName());
	}
	
	@Test
	public void play_deductsRightAmountOfOpponentShipHull() {
		// prepare
		GameStats gameStats = mock(GameStats.class);
		FightDisplayBuilder fightDisplayBuilder = new FightDisplayBuilder();
		fightDisplayBuilder.opponent().setHull(5);
		when(gameStats.getFightDisplay()).thenReturn(fightDisplayBuilder.build());
		
		// execute
		List<GameStateChange> changes = CannonMk1.play(gameStats);
		
		// verify
		assertEquals(1, changes.size());
		GameStateChange change1 = changes.get(0);
		DamageAppliedToOpponent damage = (DamageAppliedToOpponent) change1;
		assertEquals(8, damage.getHullDamage());
	}
	
	@Test
	public void getDescription_CannonMk1() {
		String description = CannonMk1.getDescription();
		assertThat(description, containsString("Cannon Mk1"));
		assertThat(description, containsString("8 damage to the hull"));
		assertThat(description, containsString("2 damage to shields"));
	}
	
	@Test
	public void getDescription_CannonMk2() {
		String description = CannonMk2.getDescription();
		assertThat(description, containsString("Cannon Mk2"));
		assertThat(description, containsString("10 damage to the hull"));
		assertThat(description, containsString("3 damage to shields"));
	}
	
	@Test
	public void getDescription_CannonMk3() {
		String description = CannonMk3.getDescription();
		assertThat(description, containsString("Cannon Mk3"));
		assertThat(description, containsString("12 damage to the hull"));
		assertThat(description, containsString("4 damage to shields"));
	}

}
