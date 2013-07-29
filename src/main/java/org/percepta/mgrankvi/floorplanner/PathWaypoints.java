package org.percepta.mgrankvi.floorplanner;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.PathWaypointState;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.DelinkedLink;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.DelinkedNode;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Link;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Node;

import com.vaadin.ui.AbstractComponent;

public class PathWaypoints extends AbstractComponent {

    private static final long serialVersionUID = -2379871101054240185L;

    public List<Node> nodes = new LinkedList<Node>();

    public void add(final Node node) {
        nodes.add(node);
        final DelinkedNode dlNode = new DelinkedNode(node.getId(), node.getPosition());
        for (final Link link : node.getLinks()) {
            dlNode.addLink(new DelinkedLink(link.getTarget().getId(), link.getWeight()));
        }
        getState().nodes.add(dlNode);
    }

    @Override
    public PathWaypointState getState() {
        return (PathWaypointState) super.getState();
    }
}
