package de.rkable.spaceTCG.card;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.display.FightDisplayBuilder;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;

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
		List<GameStateChange> changes = laser.play(gameStats);
		
		// verify
		assertEquals(1, changes.size());
		GameStateChange change1 = changes.get(0);
		DamageAppliedToOpponent damage = (DamageAppliedToOpponent) change1;
		assertEquals(3, damage.getShipDamage().getDamage());
	}

}
