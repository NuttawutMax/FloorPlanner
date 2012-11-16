package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;

//@Connect(org.percepta.mgrankvi.floorplanner.visual.entity.Room.class)
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
		getWidget().setId(getState().id);
		getWidget().setPoints(getState().getPoints());
	}
}
