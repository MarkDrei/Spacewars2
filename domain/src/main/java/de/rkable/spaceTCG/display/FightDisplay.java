package de.rkable.spaceTCG.display;

import de.rkable.spaceTCG.Fight;

/**
 * Immutable "struct" which represents information relevant for a {@link Fight}.
 *
 */
public final class FightDisplay {
	
	public final static class ShipDisplay {
		
		public ShipDisplay(int hull) {
			this.hull = hull;
		}
		
		public final int hull;
	}
	
	public final ShipDisplay player;
	public final ShipDisplay opponent;
	public final DeckDisplay deckDisplay;
	
	FightDisplay(ShipDisplay player, ShipDisplay opponent, DeckDisplay deckDisplay) {
		this.player = player;
		this.opponent = opponent;
		this.deckDisplay = deckDisplay;
	}
	
	

}
