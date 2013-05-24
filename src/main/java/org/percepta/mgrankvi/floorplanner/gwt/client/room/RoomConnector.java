package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemState;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.table.CItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.table.CTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.VConsole;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.visual.entity.Room.class)
public class RoomConnector extends AbstractComponentConnector {

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
		for (final ItemState itemState : getState().items) {
			VisualItem item;
			switch (itemState.type) {
			case TABLE:
				item = new CTable(itemState.itemPoints, itemState.itemPosition);
				item.setName(itemState.itemName);
				if (itemState.itemName != null) {
					// names.add(itemState.itemName);
				}
				break;
			default:
				item = new CItem(itemState.itemPoints, itemState.itemPosition);
			}
			getWidget().addRoomItem(item);
		}
	}

}
