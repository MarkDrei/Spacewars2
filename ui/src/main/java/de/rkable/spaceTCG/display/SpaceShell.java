package de.rkable.spaceTCG.display;

import java.util.List;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.rkable.spaceTCG.Card;
import de.rkable.spaceTCG.Fight;
import de.rkable.spaceTCG.FightEventListener;
import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.GameListener;
import de.rkable.spaceTCG.GameStateChange;
import de.rkable.spaceTCG.SpaceTcgMain;
import de.rkable.spaceTCG.map.WorldMap;

public class SpaceShell implements FightEventListener, GameListener, BackToMapListener {
	
	private Composite previousComposite;
	private Shell shell;
	private Game game;

	public SpaceShell(Display display) {
		shell = new Shell(display);
		shell.setSize(300, 600);
		shell.setLocation(100, 100);
		shell.setText("Space Mock UI");
		shell.open();
		shell.setLayout(new FillLayout());
		
//		Fight testFight = SpaceTcgMain.getTestFight();
//		previousComposite = new FightComposite(shell, testFight);
//		testFight.addFightEventListener(this);
		
		game = SpaceTcgMain.getTestGame();
		game.addGameListener(this);
		goBackToMap();
		
		shell.layout();
	}
	
	public Shell getShell() {
		return shell;
	}

	@Override
	public void cardPlayed(Card card, List<GameStateChange> changes) {
		// ignore
		
	}

	@Override
	public void victory() {
		previousComposite.dispose();
		previousComposite = new VictoryScreen(shell, this);
		shell.layout();
	}

	@Override
	public void fightInitiated(Fight fight) {
		System.out.println("Fight initiated: " + fight);
		previousComposite.dispose();
		previousComposite = new FightComposite(shell, fight);
		fight.addFightEventListener(this);
		shell.layout();
	}

	@Override
	public void goBackToMap() {
		if(previousComposite != null) {
			previousComposite.dispose();
		}
		WorldMap worldMap = game.getWorldMap();
		previousComposite = new WorldMapComposite(shell, game, worldMap);
		shell.layout();
	}

}
