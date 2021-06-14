package de.rkable.spaceTCG.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Map of waypoints on which the player can travel.
 *
 */
public class WorldMap {
	
	private final Waypoint start;
	private Waypoint currentPosition;

	public WorldMap(Waypoint start) {
		this.start = start;
		this.currentPosition = start;
		
		validateCorrectWaypointGraph();
	}
	
	private void validateCorrectWaypointGraph() {
		List<List<Waypoint>> paths = new ArrayList<>();
		
		List<Waypoint> currentPath = new ArrayList<>();
		currentPath.add(start);
		findPaths(currentPath, paths);
		
		// all paths must have the same goal
		Waypoint goal = null;
		int length = -1;
		for (List<Waypoint> path : paths) {
			Waypoint lastPathElement = path.get(path.size() - 1);
			if (goal == null) {
				goal = lastPathElement;
				length = path.size();
			}
			if (lastPathElement != goal) {
				throw new IllegalArgumentException("There is more than one goal in the map: " + goal.getName() + " and "
						+ lastPathElement.getName());
			}
			if (length != path.size()) {
				throw new IllegalArgumentException(
						"Paths of different lengths in the map: " + length + " and " + path.size());
			}
		}
	}

	private void findPaths(List<Waypoint> currentPath, List<List<Waypoint>> allPaths) {
		Waypoint lastPathElement = currentPath.get(currentPath.size() - 1);
		if (lastPathElement.getReachableWaypoints().size() == 0) {
			allPaths.add(currentPath);
		} else {
			for (Waypoint next : lastPathElement.getReachableWaypoints()) {
				List<Waypoint> nextPath = new ArrayList<>();
				nextPath.addAll(currentPath);
				nextPath.add(next);
				findPaths(nextPath, allPaths);
			}
		}
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

	public int getMapLength() {
		int length = 1;
		Waypoint next = start;
		while (next != null) {
			Set<Waypoint> reachableWaypoints = next.getReachableWaypoints();
			if (reachableWaypoints.size() > 0) {
				next = reachableWaypoints.iterator().next();
				length++;
			} else {
				next = null;
			}
		}
		return length;
	}

	public Set<Waypoint> getWaypointsOnLevel(int level) {
		HashSet<Waypoint> waypointsOnCurrentLevel = new HashSet<>();
		waypointsOnCurrentLevel.add(start);
		
		for(int currentLevel = 0; currentLevel < level; currentLevel++) {
			HashSet<Waypoint> waypointsOnNextLevel = new HashSet<>();
			for (Waypoint waypoint : waypointsOnCurrentLevel) {
				waypointsOnNextLevel.addAll(waypoint.getReachableWaypoints());
			}
			waypointsOnCurrentLevel = waypointsOnNextLevel;
		}

		return waypointsOnCurrentLevel;
	}

}
