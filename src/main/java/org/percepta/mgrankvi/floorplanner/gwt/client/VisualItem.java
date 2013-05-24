package org.percepta.mgrankvi.floorplanner.gwt.client;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.GeometryUtil;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.Widget;

public abstract class VisualItem extends Widget {

	public abstract void paint(final Context2d context);

	public abstract void paint(final Context2d context, Point offset);

	public abstract boolean pointInObject(final double x, final double y);

	public abstract void clicked(double x, double y);

	protected String id, name;
	protected final LinkedList<Point> points = new LinkedList<Point>();
	protected final List<Point> notZoomed = new LinkedList<Point>();
	protected Point position = new Point(0, 0);
	protected Point orgPosition = new Point(0, 0);
	private Double minX, maxX, minY, maxY;
	private boolean hovering = false;

	public String getId() {
		return id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setHovering(final boolean hovering) {
		this.hovering = hovering;
	}

	public boolean isHovering() {
		return hovering;
	}

	public double getPositionX() {
		return position.getX();
	}

	public double getPositionY() {
		return position.getY();
	}

	public Point getPosition() {
		return new Point(getPositionX(), getPositionY());
	}

	public void movePosition(final int x, final int y) {
		position.move(x, y);
		orgPosition.move(x, y);
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

	public void setPoints(final List<Point> points) {
		this.points.clear();
		this.points.addAll(points);
	}

	public void pointMoved() {
		minX = maxX = minY = maxY = null;
	}

	public List<Point> getPoints() {
		return new LinkedList<Point>(points);
	}

	public double minX() {
		if (minX == null) {
			minX = GeometryUtil.minX(points);
		}
		return minX;
	}

	public double minY() {
		if (minY == null) {
			minY = GeometryUtil.minY(points);
		}
		return minY;
	}

	public double maxX() {
		if (maxX == null) {
			maxX = GeometryUtil.maxX(points);
		}
		return maxX;
	}

	public double maxY() {
		if (maxY == null) {
			maxY = GeometryUtil.maxY(points);
		}
		return maxY;
	}

	public Point getCenter() {
		final int x = (int) ((maxX() - minX()) * 0.5);
		final int y = (int) ((maxY() - minY()) * 0.5);
		return new Point(x, y);
	}

	public void scale(final double scale) {
		if (notZoomed.isEmpty()) {
			copyPoints(points, notZoomed);
			orgPosition = new Point(position.getX(), position.getY());
		}
	}

	public void reset() {
		if (notZoomed.isEmpty()) {
			position = new Point(orgPosition.getX(), orgPosition.getY());
			return;
		}
		points.clear();
		copyPoints(notZoomed, points);
		position = new Point(orgPosition.getX(), orgPosition.getY());
		notZoomed.clear();
	}

	public void copyPoints(final List<Point> pointsFrom, final List<Point> pointsTo) {
		for (final Point p : pointsFrom) {
			pointsTo.add(new Point(p.getX(), p.getY()));
		}
	}

	/* Line segments intersect */
	public boolean isOnSegment(final double xi, final double yi, final double xj, final double yj, final double xk, final double yk) {
		return (xi <= xk || xj <= xk) && (xk <= xi || xk <= xj) && (yi <= yk || yj <= yk) && (yk <= yi || yk <= yj);
	}

	public int computeDirection(final int xi, final int yi, final int xj, final int yj, final int xk, final int yk) {
		final int a = (xk - xi) * (yj - yi);
		final int b = (xj - xi) * (yk - yi);
		return a < b ? -1 : a > b ? 1 : 0;
	}

	public int computeDirection(final Line line, final Point point) {
		final int a = (int) ((point.getX() - line.start.getX()) * (line.end.getY() - line.start.getY()));
		final int b = (int) ((line.end.getX() - line.start.getX()) * (point.getY() - line.start.getY()));
		return a < b ? -1 : a > b ? 1 : 0;
	}

	/**
	 * Do line segments (l1.start.getX(), l1.start.getY())--(l1.end.getX(),
	 * l1.end.getY()) and (l2.start.getX(), l2.start.getY())--(l2.end.getX(),
	 * l2.end.getY()) intersect?
	 */
	public boolean lineSegmentsIntersect(final Line l1, final Line l2) {
		final int d1 = computeDirection(l2, l1.start);
		final int d2 = computeDirection(l2, l1.end);
		final int d3 = computeDirection(l1, l2.start);
		final int d4 = computeDirection(l1, l2.end);
		return (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0)))
				|| (d1 == 0 && isOnSegment(l2.start.getX(), l2.start.getY(), l2.end.getX(), l2.end.getY(), l1.start.getX(), l1.start.getY()))
				|| (d2 == 0 && isOnSegment(l2.start.getX(), l2.start.getY(), l2.end.getX(), l2.end.getY(), l1.end.getX(), l1.end.getY()))
				|| (d3 == 0 && isOnSegment(l1.start.getX(), l1.start.getY(), l1.end.getX(), l1.end.getY(), l2.start.getX(), l2.start.getY()))
				|| (d4 == 0 && isOnSegment(l1.start.getX(), l1.start.getY(), l1.end.getX(), l1.end.getY(), l2.end.getX(), l2.end.getY()));
	}
}
