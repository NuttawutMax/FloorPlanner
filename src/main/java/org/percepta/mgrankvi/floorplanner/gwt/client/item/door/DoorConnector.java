package org.percepta.mgrankvi.floorplanner.gwt.client.item.door;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.visual.entity.Door.class)
public class DoorConnector extends AbstractComponentConnector {

	private static final long serialVersionUID = -4019614673560751414L;

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(CDoor.class);
	}

	@Override
	public CDoor getWidget() {
		return (CDoor) super.getWidget();
	};

	@Override
	public DoorState getState() {
		return (DoorState) super.getState();
	}

	@Override
	public void onStateChanged(final StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		getWidget().setPosition(getState().position);
		getWidget().setOpeningDirection(getState().openingDirection);
		getWidget().setSize(getState().size);
	}
}
