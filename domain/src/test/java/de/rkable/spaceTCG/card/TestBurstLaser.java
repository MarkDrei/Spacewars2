package de.rkable.spaceTCG.card;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.StringContains.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;

public class TestBurstLaser {
	
	@Test
	public void getDescription() {
		BurstLaser burstLaser = BurstLaser.createTier1();
		String description = burstLaser.getDescription();
		assertThat("", description, containsString("\"Burst Laser Mk1\""));
		assertThat("", description, containsString("3 laser beams"));
		assertThat("", description, containsString("1 damage to the hull"));
		assertThat("", description, containsString("3 damage to shields"));
	}
	
	@Test
	public void play_hasOneEffectPerBurstSize() {
		BurstLaser burstLaser = BurstLaser.createTier1();
		List<GameStateChange> effects = burstLaser.play(mock(GameStats.class));
		assertEquals(3, effects.size());
	}

}
