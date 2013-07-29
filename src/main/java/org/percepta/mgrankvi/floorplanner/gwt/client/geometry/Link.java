package org.percepta.mgrankvi.floorplanner.gwt.client.geometry;

public class Link {

    public final Node target;
    public final int weight;

    public Link(final Node target, final int weight) {
        this.target = target;
        this.weight = weight;
    }

}
