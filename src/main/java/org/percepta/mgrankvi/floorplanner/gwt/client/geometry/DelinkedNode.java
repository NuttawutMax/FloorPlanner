package org.percepta.mgrankvi.floorplanner.gwt.client.geometry;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class DelinkedNode implements Serializable {

    private static final long serialVersionUID = -1710389845925508445L;

    public int id;
    public List<DelinkedLink> links = new LinkedList<DelinkedLink>();
    public Point position;

    public DelinkedNode() {
    }

    public DelinkedNode(final int id, final Point position) {
        this.id = id;
        this.position = position;
    }

    public void addLink(final DelinkedLink link) {
        links.add(link);
    }
}
