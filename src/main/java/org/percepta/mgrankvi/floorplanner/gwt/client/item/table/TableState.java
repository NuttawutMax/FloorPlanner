package org.percepta.mgrankvi.floorplanner.gwt.client.item.table;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemState;

public class TableState extends ItemState {

    private static final long serialVersionUID = 6186956716133798868L;

    private List<DrawCommand> commands = new LinkedList<DrawCommand>();

    public int nodeId = -99;
    public String imageUrl = null;

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

    public List<DrawCommand> getCommands() {
        return commands;
    }

    public void setCommands(final List<DrawCommand> commands) {
        this.commands = commands;
    }

    public String getName() {
        return itemName;
    }

    public void setName(final String name) {
        itemName = name;
    }

    public void addCommand(final DrawCommand command) {
        commands.add(command);
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
