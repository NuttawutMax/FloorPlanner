package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.ArrayList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.room.CRoom;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.RoomConnector;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.RoomState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.FloorGrid.class)
public class FloorGridConnector extends AbstractComponentContainerConnector implements MenuEventHandler {

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
		// for (final ComponentConnector child : getChildComponents()) {
		// if (child instanceof RoomConnector) {
		// getWidget().addRoom(((RoomConnector) child).getWidget());
		// }
		// }
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
			for (final ComponentConnector child : getChildComponents()) {
				if (child instanceof RoomConnector) {
					final CRoom clientRoom = getWidget().getRoom(((RoomConnector) child).getWidget().getId());
					rpc.updateVisualItem(clientRoom.getId(), clientRoom.getPosition(), clientRoom.getPoints());
				}
			}
			break;
		case OPEN_ROOM_INFO:
			final CRoom clientRoom = getWidget().getRoom(event.getRoomId());
			rpc.updateVisualItem(clientRoom.getId(), clientRoom.getPosition(), clientRoom.getPoints());
			rpc.openRoomInformationWindow(event.getRoomId());
			break;
		}
	}

	@Override
	public void updateCaption(final ComponentConnector connector) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectorHierarchyChange(final ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
		// for (final ComponentConnector child : getChildComponents()) {
		// if (child instanceof RoomConnector) {
		// getWidget().addRoom(((RoomConnector) child).getWidget());
		// }
		// }
	}
}
