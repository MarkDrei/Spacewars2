package de.rkable.spaceTCG.opponent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.GameStats;
import de.rkable.spaceTCG.Opponent;
import de.rkable.spaceTCG.display.FightDisplay.ShipDisplay;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToPlayer;
import de.rkable.spaceTCG.display.FightDisplay;
import de.rkable.spaceTCG.display.ShipDisplayBuilder;

public class TestNeutralOpponent1 {
	
	Opponent opponent = new NeutralOpponent1();
	
	@Test
	public void getShip_givesShip() {
		ShipDisplayBuilder builder = new ShipDisplayBuilder();
		opponent.getShip().display(builder);
		ShipDisplay shipDisplay = builder.build();
		assertEquals(10, shipDisplay.hull);
	}
	
	@Test
	public void getNextAction_givesAction() {
		GameStats gameStats = new GameStats() {
			
			@Override
			public FightDisplay getFightDisplay() {
				return new FightDisplay(mock(ShipDisplay.class), null, null, 0, 0);
			}
		};
		List<GameStateChange> changes = opponent.performNextAction(gameStats);
		assertEquals(1, changes.size());
		GameStateChange change = changes.get(0);
		assertTrue(change instanceof DamageAppliedToPlayer);
		DamageAppliedToPlayer damage = (DamageAppliedToPlayer) change;
		
		assertEquals(2, damage.getHullDamage());
		assertEquals(5, damage.getShieldDamage());
	}
	
	@Test
	public void process_withDamage_appliesTheDamage() {
		opponent.process(new DamageAppliedToOpponent(20, 4));
		ShipDisplayBuilder displayBuilder = new ShipDisplayBuilder();
		opponent.getShip().display(displayBuilder);
		assertEquals(0, displayBuilder.build().shield);
		assertEquals(8, displayBuilder.build().hull);
	}

}
