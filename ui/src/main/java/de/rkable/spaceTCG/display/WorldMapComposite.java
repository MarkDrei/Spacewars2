package de.rkable.spaceTCG.display;

import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.rkable.spaceTCG.Game;
import de.rkable.spaceTCG.IllegalUserOperation;
import de.rkable.spaceTCG.map.Waypoint;
import de.rkable.spaceTCG.map.WorldMap;

public class WorldMapComposite extends Composite {
	
	private Game game;

	public WorldMapComposite(Shell shell, Game game, WorldMap worldMap) {
		super(shell, SWT.NONE);
		this.game = game;
		setLayout(new FillLayout(SWT.VERTICAL));
		
		Set<Waypoint> reachableWaypoints = worldMap.getReachableWaypoints();
		Waypoint currentWaypoint = worldMap.getCurrentWaypoint();
		
		for (int level = worldMap.getMapLength() - 1; level >= 0; level--) {
			addMapLevel(worldMap.getWaypointsOnLevel(level), currentWaypoint, reachableWaypoints);
		}
		
	}

	private void addMapLevel(Set<Waypoint> waypointsOnLevel, Waypoint currentWaypoint,
			Set<Waypoint> reachableWaypoints) {
		
		Composite levelContainer = new Composite(this, SWT.NONE);
		levelContainer.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		for(Waypoint waypointToAdd : waypointsOnLevel) {
			String waypointDescription = waypointToAdd.getName();
			if (currentWaypoint.equals(waypointToAdd)) {
				waypointDescription += "\n (current)";
			}
			if (reachableWaypoints.contains(waypointToAdd)) {
				waypointDescription += "\n (reachable)";
			}
			String reachableWaypointsNames = "";
			for (Waypoint next : waypointToAdd.getReachableWaypoints()) {
				reachableWaypointsNames += "\n  -> " + next.getName();
			}
			Label waypointLabel = new Label(levelContainer, SWT.NONE);
			waypointLabel.setText(waypointDescription + reachableWaypointsNames); 
			waypointLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					try {
						game.visit(waypointToAdd);
					} catch (IllegalUserOperation e1) {
						System.err.println("Cannot visit this waypoint");
						e1.printStackTrace();
					}
				}
			});
		}
		
	}

}
