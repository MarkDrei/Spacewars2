package de.rkable.spaceTCG.card;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.GameStatChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.card.gameStats.change.DamageAppliedToOpponent;
import de.rkable.spaceTCG.display.FightDisplayBuilder;

public class TestBasicLaser {
	
	private BasicLaser laser = new BasicLaser();
	
	@Test
	public void name() {
		assertEquals("Laser", laser.getName());
	}
	
	@Test
	public void play_deductsRightAmountOfOpponentShipHull() {
		// prepare
		GameStats gameStats = mock(GameStats.class);
		FightDisplayBuilder fightDisplayBuilder = new FightDisplayBuilder();
		fightDisplayBuilder.opponent().setHull(5);
		when(gameStats.getFightDisplay()).thenReturn(fightDisplayBuilder.build());
		
		// execute
		List<GameStatChange> changes = laser.play(gameStats);
		
		// verify
		assertEquals(1, changes.size());
		GameStatChange change1 = changes.get(0);
		DamageAppliedToOpponent damage = (DamageAppliedToOpponent) change1;
		assertEquals(3, damage.getDamageValue());
		assertEquals(2, damage.getRemainingOpponentHealth());
	}

}
