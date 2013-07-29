package org.percepta.mgrankvi.floorplanner.gwt.client.paint;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

public class GridButton {

    Point position;
    String color;

    final int size;

    public GridButton(final Point position, final int size, final String color) {
        this.position = position;
        this.size = size;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public void setPosition(final Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }
}
