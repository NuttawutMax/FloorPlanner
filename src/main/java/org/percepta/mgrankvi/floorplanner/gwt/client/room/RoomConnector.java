package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.VConsole;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.visual.entity.Room.class)
public class RoomConnector extends AbstractComponentContainerConnector {

	private static final long serialVersionUID = -4019614673560751414L;

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(CRoom.class);
	}

	@Override
	public CRoom getWidget() {
		return (CRoom) super.getWidget();
	};

	@Override
	public RoomState getState() {
		return (RoomState) super.getState();
	}

	@Override
	public void onStateChanged(final StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		VConsole.log(" +++ Room state changed");
		getWidget().setId(getState().id);
		getWidget().setPoints(getState().points);

		getWidget().setPosition(getState().position);

		getWidget().setName(getState().name);

		for (final DoorState door : getState().door) {
			getWidget().addDoor(door);
		}
		// for (final ItemState itemState : getState().items) {
		// VisualItem item;
		// switch (itemState.type) {
		// // case TABLE:
		// // item = new CTable(itemState.itemPoints, itemState.itemPosition);
		// // item.setName(itemState.itemName);
		// // if (itemState.itemName != null) {
		// // // names.add(itemState.itemName);
		// // }
		// // break;
		// default:
		// item = new CItem(itemState.itemPoints, itemState.itemPosition);
		// }
		// getWidget().addRoomItem(item);
		// }
	}

	@Override
	public void updateCaption(final ComponentConnector connector) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectorHierarchyChange(final ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
		final List<ComponentConnector> children = getChildComponents();
		final CRoom widget = getWidget();
		for (final ComponentConnector connector : children) {
			widget.add(connector.getWidget());
		}
	}

}
