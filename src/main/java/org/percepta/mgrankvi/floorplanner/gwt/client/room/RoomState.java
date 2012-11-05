package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.vaadin.shared.ComponentState;

public class RoomState extends ComponentState {

	private static final long serialVersionUID = -5924905171449139160L;

	private List<Point> points = new LinkedList<Point>();
	private Point position = new Point(0, 0);

	public void addPoint(final Point point) {
		points.add(point);
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(final List<Point> points) {
		this.points = points;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

}
