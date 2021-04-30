package de.rkable.spaceTCG.display;

public class FightDisplayBuilder {
	
	private final ShipDisplayBuilder playerBuilder = new ShipDisplayBuilder();
	private final ShipDisplayBuilder opponentBuilder = new ShipDisplayBuilder();
	private DeckDisplay deckDisplay = null;

	public FightDisplay build() {
		return new FightDisplay(playerBuilder.build(), opponentBuilder.build(), deckDisplay);
	}

	public ShipDisplayBuilder player() {
		return playerBuilder;
	}

	public ShipDisplayBuilder opponent() {
		return opponentBuilder;
	}
	
	public void setDeckDisplay(DeckDisplay deckDisplay) {
		this.deckDisplay = deckDisplay;
	}
	
}
