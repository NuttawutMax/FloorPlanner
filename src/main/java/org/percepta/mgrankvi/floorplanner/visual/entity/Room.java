package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.List;
import java.util.UUID;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.RoomState;

public class Room {

	private final RoomState state = new RoomState();

	public Room(final Point... points) {
		for (final Point point : points) {
			state.addPoint(point);
		}
		state.id = UUID.randomUUID().toString();
	}

	public RoomState getState() {
		return state;
	}

	public String getId() {
		return state.id;
	}

	public void setPosition(final Point position) {
		state.setPosition(position);
	}

	public void setPoints(final List<Point> points) {
		state.setPoints(points);
	}
}
