package de.rkable.spaceTCG.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestWorldMap {
	
	@Test
	public void getAllWaypoints_for2Points_findsAllWaypoints() {
		Waypoint to = new Waypoint("waypoint");
		Waypoint from = new Waypoint("waypoint", to);
		WorldMap worldMap = new WorldMap(from);
		Set<Waypoint> waypoints = worldMap.getAllWaypoints();
		assertEquals(2, waypoints.size());
		assertTrue(waypoints.contains(from));
		assertTrue(waypoints.contains(to));
	}
	
	@Test
	public void getAllWaypoints_for3Points_findsAllWaypoints() {
		Waypoint c = new Waypoint("waypoint");
		Waypoint b = new Waypoint("waypoint", c);
		Waypoint a = new Waypoint("waypoint", b);
		WorldMap worldMap = new WorldMap(a);
		Set<Waypoint> waypoints = worldMap.getAllWaypoints();
		assertEquals(3, waypoints.size());
		assertTrue(waypoints.contains(a));
		assertTrue(waypoints.contains(b));
		assertTrue(waypoints.contains(c));
	}
	
	@Test
	public void getAllWaypoints_forDiamond_findsAllWaypoints() {
		Waypoint c = new Waypoint("waypoint");
		Waypoint b1 = new Waypoint("waypoint", c);
		Waypoint b2 = new Waypoint("waypoint", c);
		Waypoint a = new Waypoint("waypoint", b1, b2);
		WorldMap worldMap = new WorldMap(a);
		Set<Waypoint> waypoints = worldMap.getAllWaypoints();
		assertEquals(4, waypoints.size());
		assertTrue(waypoints.contains(a));
		assertTrue(waypoints.contains(b1));
		assertTrue(waypoints.contains(b2));
		assertTrue(waypoints.contains(c));
	}
	
	@Test
	public void getCurrentWaypoint_givesCurrentWaypoint() {
		Waypoint waypoint = new Waypoint("waypoint");
		WorldMap worldMap = new WorldMap(waypoint);
		assertEquals(waypoint, worldMap.getCurrentWaypoint());
	}
	
	@Test
	public void getCurrentWaypoint_afterTravel_givesNewCurrentWaypoint() {
		Waypoint to = new Waypoint("waypoint");
		Waypoint from = new Waypoint("waypoint", to);
		WorldMap worldMap = new WorldMap(from);
		worldMap.travel(to);
		assertEquals(to, worldMap.getCurrentWaypoint());
	}
	
	
	@Nested
	public class ThreeWaypoints {
		Waypoint c;
		Waypoint b;
		Waypoint a;
		WorldMap worldMap;
		
		@BeforeEach
		public void setup() {
			c = new Waypoint("waypoint");
			b = new Waypoint("waypoint", c);
			a = new Waypoint("waypoint", b);
			worldMap = new WorldMap(a);
		}
		
		@Test
		public void getReachableWaypoints_for3Points_findsAllWaypoints() {
			Set<Waypoint> waypoints = worldMap.getReachableWaypoints();
			assertEquals(1, waypoints.size());
			assertTrue(waypoints.contains(b));
			
			worldMap.travel(b);
			waypoints = worldMap.getReachableWaypoints();
			assertTrue(waypoints.contains(c));
		}
		
		@Test
		public void isReachable_forCurrentWaypoint_isFalse() {
			assertFalse(worldMap.isReachable(a));
		}
		
		@Test
		public void isReachable_forReachableWaypoint_isTrue() {
			assertTrue(worldMap.isReachable(b));
		}
		
		@Test
		public void isReachable_forFarAwayWaypoint_isFalse() {
			assertFalse(worldMap.isReachable(c));
		}
		
		@Test
		public void getStart_alwaysGivesStart() {
			assertSame(a, worldMap.getStart());
			worldMap.travel(b);
			assertSame(a, worldMap.getStart());
		}
		
		@Test
		public void getMapLength_for3Levels_is3() {
			// TODO 
//			worldMap.getMapLength
		}
	}
	
	
	

}
