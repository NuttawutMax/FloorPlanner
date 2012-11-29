package org.percepta.mgrankvi.floorplanner.gwt.client.item.door;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.vaadin.shared.ComponentState;

public class DoorState extends ComponentState {

	private static final long serialVersionUID = 6186956716133798868L;

	public enum Direction {
		DOWN_LEFT, UP_LEFT, DOWN_RIGHT, UP_RIGHT
	}

	private Direction openingDirection;
	private Point position;

	public Direction getOpeningDirection() {
		return openingDirection;
	}

	public void setOpeningDirection(final Direction openingDirection) {
		this.openingDirection = openingDirection;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

}
