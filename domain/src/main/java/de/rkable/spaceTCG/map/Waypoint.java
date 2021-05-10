package de.rkable.spaceTCG.map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A waypoint is a visitable point on the GameMap
 *
 */
public class Waypoint {
	
	/**
	 * An action that is triggered when the waypoint is visited
	 */
	public interface VisitAction {
		void triggerVisit();
	}
	
	private Set<Waypoint> reachableWaypoints = new HashSet<>();
	private Optional<VisitAction> visitAction = Optional.empty();

	public Waypoint() {
		// no op
	}
	
	public Waypoint(VisitAction visitAction, Waypoint... targets) {
		this.visitAction = Optional.of(visitAction);
		reachableWaypoints.addAll(Arrays.asList(targets));
	}
	
	public Waypoint(Waypoint... targets) {
		this(() -> { /* null visit action */}, targets);
	}

	public Set<Waypoint> getReachableWaypoints() {
		return reachableWaypoints;
		
	}

	public void visit() {
		if (visitAction.isPresent()) {
			visitAction.get().triggerVisit();
		}
	}

}
