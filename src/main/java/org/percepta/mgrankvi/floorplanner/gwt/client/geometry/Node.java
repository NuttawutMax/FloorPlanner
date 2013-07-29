package org.percepta.mgrankvi.floorplanner.gwt.client.geometry;

import java.util.LinkedList;
import java.util.List;

public class Node {

    public final int id;
    public final List<Link> links = new LinkedList<Link>();
    public final Point position;

    public Node(final int id, final Point position) {
        this.id = id;
        this.position = position;
    }

    /**
     * Link both nodes with same weight in both directions.
     * 
     * @param node
     * @param weight
     */
    public void addConnectedNode(final Node node, final int weight) {
        links.add(new Link(node, weight));
        node.links.add(new Link(this, weight));
    }

    public boolean hasLinkTo(final Node node) {
        for (final Link link : links) {
            if (link.target.equals(node)) {
                return true;
            }
        }
        return false;
    }

    public Link getLinkTo(final Node node) {
        for (final Link link : links) {
            if (link.target.equals(node)) {
                return link;
            }
        }
        return null;
    }

}
