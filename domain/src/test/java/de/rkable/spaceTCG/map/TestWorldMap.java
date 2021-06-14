package de.rkable.spaceTCG.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestWorldMap {
	
	@Test
	public void constructor_withTwoGoals_throws() {
		Waypoint b1 = new Waypoint("waypoint b1");
		Waypoint b2 = new Waypoint("waypoint b2");
		Waypoint a = new Waypoint("waypoint a", b1, b2);
		assertThrows(IllegalArgumentException.class, () -> new WorldMap(a));
	}
	
	@Test
	public void constructor_withThreeGoals_throws() {
		Waypoint b1 = new Waypoint("waypoint b1");
		Waypoint b2 = new Waypoint("waypoint b2");
		Waypoint b3 = new Waypoint("waypoint b3");
		Waypoint a = new Waypoint("waypoint a", b1, b2, b3);
		assertThrows(IllegalArgumentException.class, () -> new WorldMap(a));
	}
	
	@Test
	public void constructor_withWithUnequallLongPaths_throws() {
		Waypoint d = new Waypoint("waypoint d");
		Waypoint c = new Waypoint("waypoint c", d);
		Waypoint b1 = new Waypoint("waypoint b1", c);
		Waypoint b2 = new Waypoint("waypoint b2", d);
		Waypoint a = new Waypoint("waypoint a", b1, b2);
		assertThrows(IllegalArgumentException.class, () -> new WorldMap(a));
	}

	@Nested
	public class TwoWaypoints {
		Waypoint b;
		Waypoint a;
		WorldMap worldMap;
		
		@BeforeEach
		public void setup() {
			b = new Waypoint("waypoint");
			a = new Waypoint("waypoint", b);
			worldMap = new WorldMap(a);
		}
		
		@Test
		public void getAllWaypoints_for2Points_findsAllWaypoints() {
			Set<Waypoint> waypoints = worldMap.getAllWaypoints();
			assertEquals(2, waypoints.size());
			assertTrue(waypoints.contains(a));
			assertTrue(waypoints.contains(b));
		}
		
		@Test
		public void getCurrentWaypoint_givesCurrentWaypoint() {
			assertEquals(a, worldMap.getCurrentWaypoint());
		}
		
		@Test
		public void getCurrentWaypoint_afterTravel_givesNewCurrentWaypoint() {
			worldMap.travel(b);
			assertEquals(b, worldMap.getCurrentWaypoint());
		}
		
		@Test
		public void getMapLength_for2Levels_is2() {
			assertEquals(2, worldMap.getMapLength());
		}
		
		@Test
		public void getWaypointsOnLevel() {
			Set<Waypoint> level0 = worldMap.getWaypointsOnLevel(0);
			assertEquals(1, level0.size());
			assertTrue(level0.contains(a));
			
			Set<Waypoint> level1 = worldMap.getWaypointsOnLevel(1);
			assertEquals(1, level1.size());
			assertTrue(level1.contains(b));
			
			Set<Waypoint> level2 = worldMap.getWaypointsOnLevel(2);
			assertEquals(0, level2.size());
			
			Set<Waypoint> level3 = worldMap.getWaypointsOnLevel(3);
			assertEquals(0, level3.size());
		}
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
		public void getAllWaypoints_for3Points_findsAllWaypoints() {
			Set<Waypoint> waypoints = worldMap.getAllWaypoints();
			assertEquals(3, waypoints.size());
			assertTrue(waypoints.contains(a));
			assertTrue(waypoints.contains(b));
			assertTrue(waypoints.contains(c));
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
			assertEquals(3, worldMap.getMapLength());
		}
		
		@Test
		public void getWaypointsOnLevel() {
			Set<Waypoint> level0 = worldMap.getWaypointsOnLevel(0);
			assertEquals(1, level0.size());
			assertTrue(level0.contains(a));
			
			Set<Waypoint> level1 = worldMap.getWaypointsOnLevel(1);
			assertEquals(1, level1.size());
			assertTrue(level1.contains(b));
			
			Set<Waypoint> level2 = worldMap.getWaypointsOnLevel(2);
			assertEquals(1, level2.size());
			assertTrue(level2.contains(c));
			
			Set<Waypoint> level3 = worldMap.getWaypointsOnLevel(3);
			assertEquals(0, level3.size());
		}
	}
	
	@Nested
	public class DiamondWaypoints {
		
		Waypoint c;
		Waypoint b1;
		Waypoint b2;
		Waypoint a;
		WorldMap worldMap;
		
		@BeforeEach
		public void setup() {
			c = new Waypoint("waypoint");
			b1 = new Waypoint("waypoint", c);
			b2 = new Waypoint("waypoint", c);
			a = new Waypoint("waypoint", b1, b2);
			worldMap = new WorldMap(a);
		}
		
		
		@Test
		public void getAllWaypoints_forDiamond_findsAllWaypoints() {
			Set<Waypoint> waypoints = worldMap.getAllWaypoints();
			assertEquals(4, waypoints.size());
			assertTrue(waypoints.contains(a));
			assertTrue(waypoints.contains(b1));
			assertTrue(waypoints.contains(b2));
			assertTrue(waypoints.contains(c));
		}
		
		@Test
		public void getMapLength_for3Levels_is3() {
			assertEquals(3, worldMap.getMapLength());
		}
		
		@Test
		public void getWaypointsOnLevel() {
			Set<Waypoint> level0 = worldMap.getWaypointsOnLevel(0);
			assertEquals(1, level0.size());
			assertTrue(level0.contains(a));
			
			Set<Waypoint> level1 = worldMap.getWaypointsOnLevel(1);
			assertEquals(2, level1.size());
			assertTrue(level1.contains(b1));
			assertTrue(level1.contains(b2));
			
			Set<Waypoint> level2 = worldMap.getWaypointsOnLevel(2);
			assertEquals(1, level2.size());
			assertTrue(level2.contains(c));
			
			Set<Waypoint> level3 = worldMap.getWaypointsOnLevel(3);
			assertEquals(0, level3.size());
		}
	}

}
