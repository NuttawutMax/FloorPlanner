package org.percepta.mgrankvi.floorplanner.gwt.client.geometry;

import java.io.Serializable;

public class DelinkedLink implements Serializable {

    private static final long serialVersionUID = -1563363612591205240L;

    public int target;
    public int weight;

    public DelinkedLink() {

    }

    public DelinkedLink(final int target, final int weight) {
        this.target = target;
        this.weight = weight;
    }

    public int getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

}
