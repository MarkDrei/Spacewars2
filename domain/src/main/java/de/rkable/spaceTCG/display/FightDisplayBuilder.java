package de.rkable.spaceTCG.display;

public class FightDisplayBuilder {
	
	private final ShipDisplayBuilder playerBuilder = new ShipDisplayBuilder();
	private final ShipDisplayBuilder opponentBuilder = new ShipDisplayBuilder();
	private DeckDisplay deckDisplay = null;
	private int energy = -1;
	private int maxEnergy = -1;

	public FightDisplay build() {
		return new FightDisplay(playerBuilder.build(), opponentBuilder.build(), deckDisplay, maxEnergy, energy);
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
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	
}
