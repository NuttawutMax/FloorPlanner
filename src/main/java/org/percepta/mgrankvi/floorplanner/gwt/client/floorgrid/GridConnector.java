package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractHasComponentsConnector;
import com.vaadin.client.ui.SimpleManagedLayout;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.Grid.class)
public class GridConnector extends AbstractHasComponentsConnector implements SimpleManagedLayout {

    private static final long serialVersionUID = 7482779025242695758L;

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(CGrid.class);
    }

    @Override
    public CGrid getWidget() {
        return (CGrid) super.getWidget();
    };

    @Override
    public GridState getState() {
        return (GridState) super.getState();
    }

    @Override
    public void onStateChanged(final StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        if (stateChangeEvent.hasPropertyChanged("editable")) {
            getWidget().setEditable(getState().editable);
        }
    }

    @Override
    public void updateCaption(final ComponentConnector connector) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onConnectorHierarchyChange(final ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        final List<ComponentConnector> children = getChildComponents();
        final CGrid widget = getWidget();
        widget.clear();
        for (final ComponentConnector connector : children) {
            widget.add(connector.getWidget());
        }
        for (final ComponentConnector child : connectorHierarchyChangeEvent.getOldChildren()) {
            child.getWidget().removeFromParent();
        }
    }

    @Override
    public void layout() {
        final CGrid widget = getWidget();
        if (!widget.floors.isEmpty()) {
            if (widget.selectedFloor == null) {
                widget.setFloor(widget.floors.iterator().next());
            } else if (widget.selectedFloor != null) {
                for (final CFloor floor : widget.floors) {
                    if (floor.id.equals(widget.selectedFloor.id)) {
                        widget.setFloor(floor);
                        break;
                    }
                }
            }
        }
        widget.repaint();
    }
}
