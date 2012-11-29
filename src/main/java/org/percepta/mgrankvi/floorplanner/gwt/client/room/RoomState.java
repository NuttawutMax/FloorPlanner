package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemState;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState;

import com.vaadin.shared.ComponentState;

public class RoomState extends ComponentState {

	private static final long serialVersionUID = -5924905171449139160L;

	private List<Point> points = new LinkedList<Point>();
	private Point position = new Point(0, 0);
	private List<ItemState> items = new LinkedList<ItemState>();
	private String name = "";
	private List<DoorState> door = new LinkedList<DoorState>();

	public void addPoint(final Point point) {
		points.add(point);
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(final List<Point> points) {
		this.points = points;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

	public void addItem(final ItemState item) {
		items.add(item);
	}

	public void removeItem(final ItemState item) {
		items.remove(item);
	}

	public void setItems(final List<ItemState> items) {
		this.items = items;
	}

	public List<ItemState> getItems() {
		return items;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<DoorState> getDoor() {
		return door;
	}

	public void setDoor(final List<DoorState> door) {
		this.door = door;
	}

	public void addDoor(final DoorState door) {
		this.door.add(door);
	}

}
