package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.DelinkedLink;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.DelinkedNode;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Node;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.PathGridItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.PathWaypoints.class)
public class PathWaypointConnector extends AbstractComponentConnector {

    private static final long serialVersionUID = 7482779025242695758L;

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(PathGridItem.class);
    }

    @Override
    public PathGridItem getWidget() {
        return (PathGridItem) super.getWidget();
    };

    @Override
    public PathWaypointState getState() {
        return (PathWaypointState) super.getState();
    }

    @Override
    public void onStateChanged(final StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getWidget().clearNodes();
        for (final DelinkedNode node : getState().nodes) {
            getWidget().addNode(new Node(node.id, node.position));
        }
        for (final DelinkedNode node : getState().nodes) {
            for (final DelinkedLink link : node.links) {
                getWidget().addLink(node.id, link);
            }
        }
        getWidget().buildLinks();
    }

    // @Override
    // public void updateCaption(final ComponentConnector connector) {
    // // TODO Auto-generated method stub
    // }
    //
    // @Override
    // public void onConnectorHierarchyChange(final
    // ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
    // final List<ComponentConnector> children = getChildComponents();
    // final CGrid widget = getWidget();
    // widget.clear();
    // for (final ComponentConnector connector : children) {
    // widget.add(connector.getWidget());
    // }
    // for (final ComponentConnector child :
    // connectorHierarchyChangeEvent.getOldChildren()) {
    // child.getWidget().removeFromParent();
    // }
    // }
    //
    // @Override
    // public void layout() {
    // final CGrid widget = getWidget();
    // if (!widget.floors.isEmpty()) {
    // if (widget.selectedFloor == null) {
    // widget.setFloor(widget.floors.iterator().next());
    // } else if (widget.selectedFloor != null) {
    // for (final CFloor floor : widget.floors) {
    // if (floor.id.equals(widget.selectedFloor.id)) {
    // widget.setFloor(floor);
    // break;
    // }
    // }
    // }
    // }
    // widget.repaint();
    // }
}
