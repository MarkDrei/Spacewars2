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
import de.rkable.spaceTCG.map.WorldMap;

public class SpaceShell implements FightEventListener, GameListener, GameStateListener {
	
	private Composite previousComposite;
	private Shell shell;
	private Game game;

	public SpaceShell(Display display) {
		shell = new Shell(display);
		shell.setSize(300, 600);
		shell.setLocation(800, 300);
		shell.setText("Space Mock UI");
		shell.open();
		shell.setLayout(new FillLayout());
		
		previousComposite = new NewGameComposite(shell, this, this);
		
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
		updateDisplayContent(new VictoryScreen(shell, this));
	}
	
	@Override
	public void defeat() {
		updateDisplayContent(new DefeatScreen(shell, this));
	}

	@Override
	public void fightInitiated(Fight fight) {
		System.out.println("Fight initiated: " + fight);
		fight.addFightEventListener(this);
		
		updateDisplayContent(new FightComposite(shell, fight));
	}

	@Override
	public void goBackToMap() {
		if (game == null) {
			// there is no game or map (dummy fight situation
			startOver();
			return;
		}
		WorldMap worldMap = game.getWorldMap();
		updateDisplayContent(new WorldMapComposite(shell, game, worldMap));
	}

	private void updateDisplayContent(Composite newContent) {
		if(previousComposite != null) {
			previousComposite.dispose();
		}
		previousComposite = newContent;
		shell.layout();
	}

	@Override
	public void newGame(Game newGame) {
		this.game = newGame;
		newGame.addGameListener(this);
		goBackToMap();
	}

	@Override
	public void startOver() {
		updateDisplayContent(new NewGameComposite(shell, this, this));
	}

}
