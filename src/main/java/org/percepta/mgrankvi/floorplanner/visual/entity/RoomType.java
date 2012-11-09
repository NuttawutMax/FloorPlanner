package org.percepta.mgrankvi.floorplanner.visual.entity;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Rectangle;

public class RoomType {

	public static Room square() {
		return new Room(new Rectangle(100, 100).getCorners());
	}

	public static Room LShape() {
		return new Room(new Point(0, 0), new Point(100, 0), new Point(100, 50), new Point(50, 50), new Point(50, 100), new Point(0, 100));
	}

	public static Room triangle() {
		return new Room(new Point(0, 0), new Point(100, 0), new Point(0, 100));
	}

	public static Room fivePoints() {
		return new Room(new Point(0, 0), new Point(100, 0), new Point(100, 75), new Point(75, 100), new Point(0, 100));
	}

	public static Room square(final Point offset) {
		final Room room = new Room(new Rectangle(100, 100).getCorners());
		room.setPosition(offset);
		return room;
	}

	public static Room LShape(final Point offset) {
		final Room room = new Room(new Point(0, 0), new Point(100, 0), new Point(100, 50), new Point(50, 50), new Point(50, 100), new Point(0, 100));
		room.setPosition(offset);
		return room;
	}

	public static Room triangle(final Point offset) {
		final Room room = new Room(new Point(0, 0), new Point(100, 0), new Point(0, 100));
		room.setPosition(offset);
		return room;
	}

	public static Room fivePoints(final Point offset) {
		final Room room = new Room(new Point(0, 0), new Point(100, 0), new Point(100, 75), new Point(75, 100), new Point(0, 100));
		room.setPosition(offset);
		return room;
	}

	public static Room customRoom(final Point offset, final Point... points) {
		final Room room = new Room(points);
		room.setPosition(offset);
		return room;
	}
}
