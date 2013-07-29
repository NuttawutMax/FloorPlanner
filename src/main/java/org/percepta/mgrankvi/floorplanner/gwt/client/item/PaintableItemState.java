package org.percepta.mgrankvi.floorplanner.gwt.client.item;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.Circle;

public class PaintableItemState extends ItemState {

    private static final long serialVersionUID = 6186956716133798868L;

    List<Circle> circles = new LinkedList<Circle>();
    List<Line> lines = new LinkedList<Line>();

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

    public void addPoint(final Point point) {
        itemPoints.add(point);
    }

    public void addPoints(final Point... points) {
        for (final Point point : points) {
            itemPoints.add(point);
        }
    }

}
