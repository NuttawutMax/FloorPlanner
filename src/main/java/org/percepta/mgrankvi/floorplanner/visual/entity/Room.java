package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.List;
import java.util.UUID;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.DoorState;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.DoorState.Direction;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.RoomState;

import com.vaadin.ui.AbstractComponent;

public class Room extends AbstractComponent {

	private static final long serialVersionUID = 1050249067936900003L;

	public Room(final Point... points) {
		for (final Point point : points) {
			getState().addPoint(point);
		}
		getState().id = UUID.randomUUID().toString();
	}

	@Override
	public RoomState getState() {
		return (RoomState) super.getState();
	}

	@Override
	public String getId() {
		return getState().id;
	}

	public void setPosition(final Point position) {
		getState().setPosition(position);
	}

	public void setPoints(final List<Point> points) {
		getState().setPoints(points);
	}

	public void setName(final String name) {
		getState().setName(name);
	}

	public String getName() {
		return getState().getName();
	}

	public void addDoor(final Direction openingDirection, final Point position) {
		final DoorState door = new DoorState();
		door.setOpeningDirection(openingDirection);
		door.setPosition(position);
		getState().addDoor(door);
	}
}
