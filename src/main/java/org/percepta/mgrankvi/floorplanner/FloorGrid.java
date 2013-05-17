package org.percepta.mgrankvi.floorplanner;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.FloorGridServerRpc;
import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.FloorGridState;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.visual.entity.Room;
import org.percepta.mgrankvi.floorplanner.visual.entity.RoomCreator;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Notification;

public class FloorGrid extends AbstractComponent implements HasComponents {

	private static final long serialVersionUID = 6976301197921043544L;

	private final List<Component> components = new LinkedList<Component>();

	private final FloorGridServerRpc rpc = new FloorGridServerRpc() {
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
			FloorGrid.this.removeRoom(toRemove);
		}

		@Override
		public void openRoomInformationWindow(final String id) {
			Notification.show("Room information for: " + findRoom(id));
		}

		@Override
		public void updateVisualItem(final String id, final Point position, final List<Point> points) {
			for (final Component component : components) {
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
		for (final Component component : components) {
			if (component instanceof Room) {
				final Room room = (Room) component;
				if (room.getId().equals(id)) {
					found = room;
				}
			}
		}
		return found;
	}

	public FloorGrid() {
		registerRpc(rpc);
		setWidth("100%");
		setHeight("100%");
	}

	public void addRoom(final Room room) {
		components.add(room);
		if (room.getParent() != this) {
			room.setParent(this);
		}
		getState().addRoom(room.getState());
	}

	public void removeRoom(final Room room) {
		components.remove(room);
		room.setParent(null);
		getState().removeRoom(room.getState());
	}

	public void setEditable(final boolean editable) {
		getState().editable = editable;
	}

	public boolean getEditable() {
		return getState().editable;
	}

	@Override
	protected FloorGridState getState() {
		return (FloorGridState) super.getState();
	}

	@Override
	public Iterator<Component> iterator() {
		return components.iterator();
	}

	// @Override
	// public boolean isComponentVisible(final Component childComponent) {
	// return true;
	// }

}
