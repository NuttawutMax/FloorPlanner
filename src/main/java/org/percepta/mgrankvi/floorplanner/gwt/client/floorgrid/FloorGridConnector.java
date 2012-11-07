package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.ArrayList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.room.CRoom;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.RoomState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.VConsole;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.FloorGrid.class)
public class FloorGridConnector extends AbstractComponentConnector implements MenuEventHandler {

	private static final long serialVersionUID = 7482779025242695758L;

	private final FloorGridServerRpc rpc = RpcProxy.create(FloorGridServerRpc.class, this);
	private final List<HandlerRegistration> handlerRegistration = new ArrayList<HandlerRegistration>();

	@Override
	public void init() {
		super.init();

		handlerRegistration.add(getWidget().addMenuEventHandler(this));
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(CFloorGrid.class);
	}

	@Override
	public CFloorGrid getWidget() {
		return (CFloorGrid) super.getWidget();
	};

	@Override
	public FloorGridState getState() {
		return (FloorGridState) super.getState();
	}

	@Override
	public void onStateChanged(final StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		for (final RoomState room : getState().rooms) {
			getWidget().addRoom(room);
		}
		VConsole.log("StateChanged: " + getState().rooms.size() + " rooms.");
	}

	@Override
	public void onMenuEvent(final MenuEvent event) {
		switch (event.getEventType()) {
		case ADD_ROOM:
			rpc.addNewRoom(event.getValue(), event.getX(), event.getY());
			break;
		case REMOVE_ROOM:
			rpc.removeRoom(event.getRoomId());
			break;
		case UPDATE_ROOMS:
			for (final RoomState item : getState().rooms) {
				final CRoom clientRoom = getWidget().getRoom(item.id);
				rpc.updateVisualItem(clientRoom.getId(), clientRoom.getPosition(), clientRoom.getPoints());
			}
			break;
		case OPEN_ROOM_INFO:
			rpc.openRoomInformationWindow(event.getRoomId());
			break;
		}
	}
}
