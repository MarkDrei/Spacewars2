package de.rkable.spaceTCG.map;

import java.util.HashSet;
import java.util.Set;

/**
 * A Map of waypoints on which the player can travel 
 *
 */
public class WorldMap {
	
	private final Waypoint start;
	private Waypoint currentPosition;

	public WorldMap(Waypoint start) {
		this.start = start;
		this.currentPosition = start;
	}

	public Set<Waypoint> getAllWaypoints() {
		Set<Waypoint> waypoints = new HashSet<>();
		waypoints.add(start);
		findAllWaypoints(start, waypoints);
		
		return waypoints;
	}

	private void findAllWaypoints(Waypoint position, Set<Waypoint> waypoints) {
		for (Waypoint waypoint : position.getReachableWaypoints()) {
			waypoints.add(waypoint);
			findAllWaypoints(waypoint, waypoints);
		}
	}

	public Waypoint getCurrentWaypoint() {
		return currentPosition;
	}

	public void travel(Waypoint to) {
		this.currentPosition = to;
		to.visit();
	}

	public Set<Waypoint> getReachableWaypoints() {
		return currentPosition.getReachableWaypoints();
	}

	public boolean isReachable(Waypoint waypoint) {
		return currentPosition.getReachableWaypoints().contains(waypoint);
	}

	public Waypoint getStart() {
		return start;
	}

}
