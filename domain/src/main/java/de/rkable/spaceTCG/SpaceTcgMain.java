package de.rkable.spaceTCG;

import de.rkable.spaceTCG.card.BasicLaser;

public class SpaceTcgMain {
	
	public static Fight getTestFight() {
		GameDeck gameDeck = new GameDeck();
		gameDeck.addCardToDiscardPile(new BasicLaser("Laser 1", 1));
		gameDeck.addCardToDiscardPile(new BasicLaser("Laser 2", 2));
		gameDeck.addCardToDiscardPile(new BasicLaser("Laser 3", 3));
		gameDeck.addCardToDiscardPile(new BasicLaser("Laser 4", 4));
		
		Ship player = new Ship(50);
		Ship opponent = new Ship(20);
		return new Fight(player, opponent, gameDeck);
	}

}
