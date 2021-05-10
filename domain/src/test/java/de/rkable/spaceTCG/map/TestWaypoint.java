package de.rkable.spaceTCG.map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import de.rkable.spaceTCG.map.Waypoint.VisitAction;

public class TestWaypoint {

	@Test
	public void getReachableWaypoints_givesWaypoint() {
		Waypoint to = new Waypoint();
		Set<Waypoint> reachable = to.getReachableWaypoints();
		assertEquals(0, reachable.size());
		
		Waypoint from = new Waypoint(to);
		reachable = from.getReachableWaypoints();
		assertEquals(1, reachable.size());
		assertTrue(reachable.contains(to));
	}
	
	@Test
	public void getReachableWaypoints_withToWaypoints_givesWaypoints() {
		Waypoint to1 = new Waypoint();
		Waypoint to2 = new Waypoint();
		
		Waypoint from = new Waypoint(to1, to2);
		Set<Waypoint> reachable = from.getReachableWaypoints();
		assertEquals(2, reachable.size());
		assertTrue(reachable.contains(to1));
		assertTrue(reachable.contains(to2));
	}
	
	@Test
	public void visit_triggersEvent() {
		VisitAction visitAction = mock(Waypoint.VisitAction.class);
		Waypoint waypoint = new Waypoint(visitAction);
		waypoint.visit();
		verify(visitAction).triggerVisit();
	}
}
