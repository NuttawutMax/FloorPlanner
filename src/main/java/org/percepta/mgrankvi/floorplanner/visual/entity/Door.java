package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.UUID;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemType;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState.Direction;

public class Door extends AbstractItem {

	private static final long serialVersionUID = -8375292297427422389L;

	public Door() {
		getState().type = ItemType.DOOR;
		getState().id = UUID.randomUUID().toString();
	}

	public Door(final Direction openingDirection, final Point position, final int size) {
		getState().type = ItemType.DOOR;
		getState().id = UUID.randomUUID().toString();

		getState().position = position;
		getState().size = size;
		getState().openingDirection = openingDirection;
	}

	public void setPosition(final Point position) {
		getState().position = position;
	}

	@Override
	public DoorState getState() {
		return (DoorState) super.getState();
	}
}
