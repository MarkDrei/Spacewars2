package de.rkable.spaceTCG.card;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.StringContains.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

public class TestMinigun {
	
	Minigun MinigunMk1 = Minigun.createTier1();
	Minigun MinigunMk2 = Minigun.createTier2();
	Minigun MinigunMk3 = Minigun.createTier3();
	
	@Test
	public void getDescription_forMk1() {
		String description = MinigunMk1.getDescription();
		assertThat("", description, containsString("\"Minigun Mk1\""));
		assertThat("", description, containsString("3 ballistic bullets"));
		assertThat("", description, containsString("3 damage to the hull"));
		assertThat("", description, containsString("1 damage to shields"));
	}
	
	@Test
	public void getDescription_forMk2() {
		String description = MinigunMk2.getDescription();
		assertThat("", description, containsString("\"Minigun Mk2\""));
		assertThat("", description, containsString("4 ballistic bullets"));
		assertThat("", description, containsString("3 damage to the hull"));
		assertThat("", description, containsString("1 damage to shields"));
	}
	
	@Test
	public void getDescription_forMk3() {
		String description = MinigunMk3.getDescription();
		assertThat("", description, containsString("\"Minigun Mk3\""));
		assertThat("", description, containsString("5 ballistic bullets"));
		assertThat("", description, containsString("3 damage to the hull"));
		assertThat("", description, containsString("1 damage to shields"));
	}
	
	@Test
	public void play_hasOneEffectPerBurstSize() {
		List<GameStateChange> effects;
		effects = MinigunMk1.play(mock(GameStats.class));
		assertEquals(3, effects.size());
		assertTrue(effects.get(0) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(1) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(2) instanceof DamageAppliedToOpponent);
		
		effects = MinigunMk2.play(mock(GameStats.class));
		assertEquals(4, effects.size());
		assertTrue(effects.get(0) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(1) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(2) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(3) instanceof DamageAppliedToOpponent);
		
		effects = MinigunMk3.play(mock(GameStats.class));
		assertEquals(5, effects.size());
		assertTrue(effects.get(0) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(1) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(2) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(3) instanceof DamageAppliedToOpponent);
		assertTrue(effects.get(4) instanceof DamageAppliedToOpponent);
	}

}
