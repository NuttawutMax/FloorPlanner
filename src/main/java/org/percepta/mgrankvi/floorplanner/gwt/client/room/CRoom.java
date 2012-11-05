package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.GeometryUtil;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class CRoom extends VisualItem {

	private final LinkedList<Point> points = new LinkedList<Point>();
	private Point position = new Point(0, 0);
	private boolean selected = false;
	private final String id;

	public CRoom(final String id, final List<Point> points, final Point position) {
		this.points.addAll(points);
		this.position = position;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

	public void movePosition(final int x, final int y) {
		position.setX(position.getX() + x);
		position.setY(position.getY() + y);
	}

	public void setSelection(final boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public List<Point> getPoints() {
		return new LinkedList<Point>(points);
	}

	public int getPositionX() {
		return position.getX();
	}

	public int getPositionY() {
		return position.getY();
	}

	@Override
	public void paint(final Context2d context) {

		final Point first = points.getFirst();
		if (selected) {
			context.setStrokeStyle(CssColor.make("GREEN"));
		} else {
			context.setStrokeStyle(CssColor.make("BLACK"));
		}

		context.beginPath();
		context.moveTo(position.getX() + first.getX(), position.getY() + first.getY());

		for (final Point point : points) {
			context.lineTo(position.getX() + point.getX(), position.getY() + point.getY());
		}
		context.lineTo(position.getX() + first.getX(), position.getY() + first.getY());

		context.closePath();
		context.stroke();
		if (selected) {
			context.setStrokeStyle(CssColor.make("PURPLE"));

			context.beginPath();

			for (final Point point : points) {
				context.rect(position.getX() + point.getX() - 2, position.getY() + point.getY() - 2, 5, 5);
			}

			context.closePath();
			context.stroke();
		}
	}

	@Override
	public boolean pointInObject(final int x, final int y) {
		if (x < (position.getX() + minX(points)) || x > (position.getX() + maxX(points)) || y < (position.getY() + minY(points))
				|| y > (position.getY() + maxY(points))) {
			return false;
		}

		final Point target = new Point(x, y);
		final Line targetLine = new Line(new Point(position.getX() + minX(points) - 50, position.getY() + minY(points) - 50), target);
		int intercepts = 0;
		for (int i = 0; i + 2 <= points.size(); i++) {
			final Line line = new Line(combine(position, points.get(i)), combine(position, points.get(i + 1)));
			if (lineSegmentsIntersect(line, targetLine)) {
				intercepts++;
			}
		}
		final Line line = new Line(combine(position, points.getFirst()), combine(position, points.getLast()));
		if (lineSegmentsIntersect(line, targetLine)) {
			intercepts++;
		}

		return intercepts % 2 == 1;
	}

	public Point selectedPoint(final int x, final int y) {
		for (final Point point : points) {
			final int pointXMin = position.getX() + point.getX() - 4;
			final int pointYMin = position.getY() + point.getY() - 4;
			if (x > pointXMin && x < pointXMin + 8 && y > pointYMin && y < pointYMin + 8) {
				return point;
			}
		}
		return null;
	}

	private Point combine(final Point p1, final Point p2) {
		return GeometryUtil.combine(p1, p2);
	}
}
