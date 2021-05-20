package de.rkable.spaceTCG.display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.rkable.spaceTCG.Fight;
import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.GameListener;
import de.rkable.spaceTCG.SpaceTcgMain;

public class NewGameComposite extends Composite {

	public NewGameComposite(Composite parent, GameListener gameListener,
			GameStateListener gameStateListener) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout(SWT.VERTICAL));
		
		Label dummyFight = new Label(this, SWT.NONE);
		dummyFight.setText("Start new dummy fight");
		dummyFight.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Fight testFight = SpaceTcgMain.getTestFight();
				gameListener.fightInitiated(testFight);
			}
		});
		
		Label dummyGame = new Label(this, SWT.NONE);
		dummyGame.setText("Start new dummy game");
		
		dummyGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Game game = SpaceTcgMain.getTestGame();
				gameStateListener.newGame(game);
			}
		});
	}

}
