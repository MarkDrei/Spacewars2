package de.rkable.spaceTCG;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import de.rkable.spaceTCG.display.FightDisplay;

public class TestFight {
	
	@Test
	public void hasUserLost_whenShipIsAlive_isFalse() {
		Fight fight = new Fight(new Ship(1), new Ship(1), mock(GameDeck.class));
		assertFalse(fight.hasUserLost());
	}
	
	@Test
	public void hasUserLost_whenShipIsNotAlive_isTrue() {
		Fight fight = new Fight(new Ship(0), new Ship(1), mock(GameDeck.class));
		assertTrue(fight.hasUserLost());
	}
	
	@Test
	public void hasUserWon_whenOpponentIsAlive_isFalse() {
		Fight fight = new Fight(new Ship(1), new Ship(1), mock(GameDeck.class));
		assertFalse(fight.hasUserWon());
	}
	
	@Test
	public void hasUserWon_whenOpponentIsNotAlive_isTrue() {
		Fight fight = new Fight(new Ship(1), new Ship(0), mock(GameDeck.class));
		assertTrue(fight.hasUserWon());
	}
	
	@Test
	public void getDrawnCards_gives4Cards() {
		GameDeck gameDeck = mock(GameDeck.class);
		when(gameDeck.drawCard()).thenReturn(null);
		when(gameDeck.getDrawnCards()).thenReturn(Arrays.asList(
				mock(Card.class),
				mock(Card.class),
				mock(Card.class),
				mock(Card.class)
				));
		Fight fight = new Fight(new Ship(1), new Ship(0), gameDeck);
		List<Card> cards = fight.getDrawnCards();
		assertEquals(4, cards.size());
		verify(gameDeck, times(4)).drawCard();
	}
	
	@Test
	public void display_givesDisplay() {
		Fight fight = new Fight(new Ship(1), new Ship(0), mock(GameDeck.class, RETURNS_DEEP_STUBS));
		FightDisplay display = fight.display();
		assertNotNull(display);
		assertEquals(1, display.player.hull);
	}

}
