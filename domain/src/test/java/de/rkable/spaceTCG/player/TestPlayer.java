package de.rkable.spaceTCG.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.Ship;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToOpponent;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToPlayer;
import de.rkable.spaceTCG.gameStats.change.DamageAppliedToShip;
import de.rkable.spaceTCG.gameStats.change.RechargePlayerShield;
import de.rkable.spaceTCG.gameStats.change.RechargeShipShield;

public class TestPlayer {
	
	private Ship shipMock;
	private Player player;
	private PlayerDeck playerDeck;
	
	@BeforeEach
	public void setup() {
		shipMock = mock(Ship.class);
		playerDeck = mock(PlayerDeck.class);
		player = new Player(shipMock, playerDeck);
	}
	
	@Test
	public void getDeck() {
		PlayerDeck deck = player.getDeck();
		assertSame(playerDeck, deck);
	}
	
	@Test
	public void process_withUnknownGameStateChange_doesNothing() {
		verifyGameStateChangeDoesNothing(new GameStateChange() {
			// nop
		});
		verifyGameStateChangeDoesNothing(new DamageAppliedToOpponent(2, 2));
	}
	
	private void verifyGameStateChangeDoesNothing(GameStateChange change) {
		player.process(change);
		verify(shipMock, never()).process(any());
	}
	
	@Test
	public void process_withPlayerDamage_forwardsProcessingToShip() {
		player.process(new DamageAppliedToPlayer(5, 2));
		verify(shipMock).process(any(DamageAppliedToShip.class));
	}
	
	@Test
	public void process_withRechargePlayerShields_forwardsProcessingToShip() {
		player.process(new RechargePlayerShield(10));
		verify(shipMock).process(any(RechargeShipShield.class));
	}

}
