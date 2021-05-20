package de.rkable.spaceTCG.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		
		Set<Waypoint> allWaypoints = worldMap.getAllWaypoints();
		
		Map<Waypoint, String> waypointNames = new HashMap<>();
		int waypointNumber = 1;
		for (Waypoint waypoint : allWaypoints) {
			waypointNames.put(waypoint, "Waypoint " + waypointNumber++);
		}
		
		Set<Waypoint> reachableWaypoints = worldMap.getReachableWaypoints();
		Waypoint currentWaypoint = worldMap.getCurrentWaypoint();
		
		List<Waypoint> alreadyAddedWaypoints = new ArrayList<>();
		addWaypoint(worldMap.getStart(), alreadyAddedWaypoints, currentWaypoint, reachableWaypoints, waypointNames);
	}

	private void addWaypoint(Waypoint waypointToAdd, List<Waypoint> alreadyAddedWaypoints, Waypoint currentWaypoint,
			Set<Waypoint> reachableWaypoints, Map<Waypoint, String> waypointNames) {
		
		
		if (alreadyAddedWaypoints.contains(waypointToAdd)) { 
			return;
		}
		alreadyAddedWaypoints.add(waypointToAdd);
		String waypointDescription = waypointNames.get(waypointToAdd);
		if (currentWaypoint.equals(waypointToAdd)) {
			waypointDescription += " (current)";
		}
		if (reachableWaypoints.contains(waypointToAdd)) {
			waypointDescription += " (reachable)";
		}
		String reachableWaypointsNames = "";
		for (Waypoint next : waypointToAdd.getReachableWaypoints()) {
			reachableWaypointsNames += "\n  -> " + waypointNames.get(next);
		}
		Label waypointLabel = new Label(this, SWT.NONE);
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
		
		for (Waypoint next : waypointToAdd.getReachableWaypoints()) {
			addWaypoint(next, alreadyAddedWaypoints, currentWaypoint, reachableWaypoints, waypointNames);
		}
	}

}
