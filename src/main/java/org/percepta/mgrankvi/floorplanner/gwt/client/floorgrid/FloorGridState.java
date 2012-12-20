package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.room.RoomState;

import com.vaadin.shared.AbstractComponentState;

public class FloorGridState extends AbstractComponentState {

	private static final long serialVersionUID = -863840405464639721L;

	public boolean editable = false;
	List<RoomState> rooms = new LinkedList<RoomState>();

	public List<RoomState> getRooms() {
		return rooms;
	}

	public void setRooms(final List<RoomState> rooms) {
		this.rooms = rooms;
	}

	public void addRoom(final RoomState room) {
		rooms.add(room);
	}

	public void removeRoom(final RoomState room) {
		rooms.remove(room);
	}

}
