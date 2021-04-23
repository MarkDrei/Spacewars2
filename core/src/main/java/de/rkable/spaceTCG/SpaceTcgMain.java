package de.rkable.spaceTCG;

import de.rkable.spaceTCG.card.BasicLaser;

public class SpaceTcgMain {
	
	public static Fight getTestFight() {
		GameDeck gameDeck = new GameDeck();
		gameDeck.addCardToDiscardPile(new BasicLaser());
		gameDeck.addCardToDiscardPile(new BasicLaser());
		gameDeck.addCardToDiscardPile(new BasicLaser());
		gameDeck.addCardToDiscardPile(new BasicLaser());
		
		Ship player = new Ship(50);
		Ship opponent = new Ship(50);
		return new Fight(player, opponent, gameDeck);
	}

}
