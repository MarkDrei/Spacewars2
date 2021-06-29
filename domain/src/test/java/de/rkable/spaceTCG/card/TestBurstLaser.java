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

public class TestBurstLaser {
	
	Card burstLaserMk1 = BurstLaser.FACTORY.createTier1();
	Card burstLaserMk2 = BurstLaser.FACTORY.createTier2();
	Card burstLaserMk3 = BurstLaser.FACTORY.createTier3();
	
	@Test
	public void getDescription_forMk1() {
		String description = burstLaserMk1.getDescription();
		assertThat("", description, containsString("\"Burst Laser Mk1\""));
		assertThat("", description, containsString("3 laser beams"));
		assertThat("", description, containsString("1 damage to the hull"));
		assertThat("", description, containsString("3 damage to shields"));
	}
	
	@Test
	public void getDescription_forMk2() {
		String description = burstLaserMk2.getDescription();
		assertThat("", description, containsString("\"Burst Laser Mk2\""));
		assertThat("", description, containsString("4 laser beams"));
		assertThat("", description, containsString("1 damage to the hull"));
		assertThat("", description, containsString("3 damage to shields"));
	}
	
	@Test
	public void getDescription_forMk3() {
		String description = burstLaserMk3.getDescription();
		assertThat("", description, containsString("\"Burst Laser Mk3\""));
		assertThat("", description, containsString("5 laser beams"));
		assertThat("", description, containsString("1 damage to the hull"));
		assertThat("", description, containsString("3 damage to shields"));
	}
	
	@Test
	public void play_hasOneEffectPerBurstSize() {
		List<GameStateChange> effects;
		effects = burstLaserMk1.play(mock(GameStats.class));
		assertEquals(3, effects.size());
		
		effects = burstLaserMk2.play(mock(GameStats.class));
		assertEquals(4, effects.size());
		
		effects = burstLaserMk3.play(mock(GameStats.class));
		assertEquals(5, effects.size());
	}

}
