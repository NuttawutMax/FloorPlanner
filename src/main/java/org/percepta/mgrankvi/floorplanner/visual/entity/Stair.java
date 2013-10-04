package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Rectangle;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemType;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.stairs.StairState;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.Circle;

public class Stair extends AbstractItem {

    private static final long serialVersionUID = -8375292297427422389L;

    public Stair() {
        getState().type = ItemType.COMBINED;
        getState().id = UUID.randomUUID().toString();
    }

    public Stair(final String name, final Point position) {
        getState().type = ItemType.COMBINED;
        getState().id = UUID.randomUUID().toString();
        setName(name);
        setPosition(position);
    }

    public void setName(final String name) {
        getState().setName(name);
    }

    public String getName() {
        return getState().getName();
    }

    public void setSize(final int width, final int height) {
        getState().addPoints(new Rectangle(width, height).getCorners());
    }

    public void setPosition(final Point position) {
        getState().setPosition(position);
    }

    public void linkToNode(final int nodeId) {
        getState().nodeId = nodeId;
    }

    public void addCircle(final Circle circle) {
        getState().circle = circle;
    }

    public void addLine(final Line line) {
        List<Line> lineList = getState().lineList;
        if (lineList == null) {
            lineList = new LinkedList<Line>();
            getState().lineList = lineList;
        }
        lineList.add(line);
    }

    public void setSegmentCircle(final double degrees) {
        getState().degrees = degrees;
    }

    @Override
    public StairState getState() {
        return (StairState) super.getState();
    }
}
