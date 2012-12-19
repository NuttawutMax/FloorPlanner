package org.percepta.mgrankvi.floorplanner.gwt.client.item.door;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.vaadin.shared.AbstractComponentState;

public class DoorState extends AbstractComponentState {

	private static final long serialVersionUID = 6186956716133798868L;

	public enum Direction {
		DOWN_LEFT, UP_LEFT, DOWN_RIGHT, UP_RIGHT
	}

	private Direction openingDirection;
	private Point position;
	private int size = 20;

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

	public int getSize() {
		return size;
	}

	public void setSize(final int size) {
		this.size = size;
	}

}
