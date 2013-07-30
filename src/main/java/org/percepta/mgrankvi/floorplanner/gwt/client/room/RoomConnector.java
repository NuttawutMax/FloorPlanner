package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
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

        getWidget().setId(getState().id);
        getWidget().setPoints(getState().points);

        getWidget().setPosition(getState().position);

        getWidget().setName(getState().name);
        if (getState().color != null) {
            getWidget().setColor(getState().color);
        }
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
