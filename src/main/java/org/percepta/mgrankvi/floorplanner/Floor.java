package org.percepta.mgrankvi.floorplanner;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.FloorServerRpc;
import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.FloorState;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.visual.entity.Room;
import org.percepta.mgrankvi.floorplanner.visual.entity.RoomCreator;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Notification;

public class Floor extends AbstractComponentContainer implements HasComponents {

	private static final long serialVersionUID = 6976301197921043544L;

	List<Component> children = new LinkedList<Component>();

	private final FloorServerRpc rpc = new FloorServerRpc() {
		private static final long serialVersionUID = -1335858817145455775L;

		@Override
		public void addNewRoom(final int value, final int x, final int y) {
			switch (value) {
			case 3:
				addRoom(RoomCreator.triangle(new Point(x, y)));
				break;
			case 4:
				addRoom(RoomCreator.square(new Point(x, y)));
				break;
			case 5:
				addRoom(RoomCreator.fivePoints(new Point(x, y)));
				break;
			case 6:
				addRoom(RoomCreator.LShape(new Point(x, y)));
				break;
			}
		}

		@Override
		public void removeRoom(final String id) {
			final Room toRemove = findRoom(id);
			Floor.this.removeRoom(toRemove);
		}

		@Override
		public void openRoomInformationWindow(final String id) {
			Notification.show("Room information for: " + findRoom(id));
		}

		@Override
		public void updateVisualItem(final String id, final Point position, final List<Point> points) {
			for (final Component component : children) {
				if (component instanceof Room) {
					final Room room = (Room) component;
					if (room.getId().equals(id)) {
						room.setPosition(position);
						room.setPoints(points);
						break;
					}
				}
			}
		}
	};

	private Room findRoom(final String id) {
		Room found = null;
		for (final Component component : children) {
			if (component instanceof Room) {
				final Room room = (Room) component;
				if (room.getId().equals(id)) {
					found = room;
				}
			}
		}
		return found;
	}

	public Floor() {
		registerRpc(rpc);
		setWidth("100%");
		setHeight("100%");
	}

	public void addRoom(final Room room) {
		addComponent(room);
	}

	public void removeRoom(final Room room) {
		removeComponent(room);
	}

	@Override
	protected FloorState getState() {
		return (FloorState) super.getState();
	}

	@Override
	public void addComponent(final Component c) {
		children.add(c);
		super.addComponent(c);
		markAsDirty();
	}

	@Override
	public void removeComponent(final Component c) {
		children.remove(c);
		super.removeComponent(c);
		markAsDirty();
	}

	@Override
	public void replaceComponent(final Component oldComponent, final Component newComponent) {
		final int index = children.indexOf(oldComponent);
		if (index != -1) {
			children.remove(index);
			children.add(index, newComponent);
			fireComponentDetachEvent(oldComponent);
			fireComponentAttachEvent(newComponent);
			markAsDirty();
		}
	}

	@Override
	public int getComponentCount() {
		return children.size();
	}

	@Override
	public Iterator<Component> iterator() {
		return children.iterator();
	}
}
