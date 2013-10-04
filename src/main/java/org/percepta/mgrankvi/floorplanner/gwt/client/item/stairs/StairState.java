package org.percepta.mgrankvi.floorplanner.gwt.client.item.stairs;

import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemState;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.Circle;

public class StairState extends ItemState {

    private static final long serialVersionUID = 6186956716133798868L;

    public int nodeId = -99;
    public Circle circle;
    public List<Line> lineList;
    public Double degrees;

    public Point getPosition() {
        return itemPosition;
    }

    public void setPosition(final Point position) {
        itemPosition = position;
    }

    public List<Point> getPoints() {
        return itemPoints;
    }

    public void setPoints(final List<Point> points) {
        itemPoints = points;
    }

    public String getName() {
        return itemName;
    }

    public void setName(final String name) {
        itemName = name;
    }

    public void addPoint(final Point point) {
        itemPoints.add(point);
    }

    public void addPoints(final Point... points) {
        for (final Point point : points) {
            itemPoints.add(point);
        }
    }

    public void setLines(final Line... lines) {
        for (final Line line : lines) {
            lineList.add(line);
        }
    }

    public void addLine(final Line line) {
        lineList.add(line);
    }

    public List<Line> getLines() {
        return lineList;
    }
}
