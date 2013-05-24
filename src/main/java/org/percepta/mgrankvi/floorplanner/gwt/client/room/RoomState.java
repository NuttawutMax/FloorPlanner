package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemState;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState;

import com.vaadin.shared.AbstractComponentState;

public class RoomState extends AbstractComponentState {

	private static final long serialVersionUID = -5924905171449139160L;

	public List<Point> points = new LinkedList<Point>();
	public Point position = new Point(0, 0);
	public List<ItemState> items = new LinkedList<ItemState>();
	public String name = "";
	public List<DoorState> door = new LinkedList<DoorState>();

	public void addPoint(final Point point) {
		points.add(point);
	}

	public void addItem(final ItemState item) {
		items.add(item);
	}

	public void removeItem(final ItemState item) {
		items.remove(item);
	}

	public void addDoor(final DoorState door) {
		this.door.add(door);
	}

}
