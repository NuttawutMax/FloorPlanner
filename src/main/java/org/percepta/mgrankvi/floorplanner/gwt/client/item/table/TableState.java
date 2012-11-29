package org.percepta.mgrankvi.floorplanner.gwt.client.item.table;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemState;

public class TableState extends ItemState {

	private static final long serialVersionUID = 6186956716133798868L;

	private Point position;
	private List<DrawCommand> commands = new LinkedList<DrawCommand>();
	private String name;

	public Point getPosition() {
		return position;
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(final List<Point> points) {
		this.points = points;
	}

	public List<DrawCommand> getCommands() {
		return commands;
	}

	public void setCommands(final List<DrawCommand> commands) {
		this.commands = commands;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void addCommand(final DrawCommand command) {
		commands.add(command);
	}

	public void addPoint(final Point point) {
		points.add(point);
	}

	public void addPoints(final Point... points) {
		for (final Point point : points) {
			this.points.add(point);
		}
	}
}
