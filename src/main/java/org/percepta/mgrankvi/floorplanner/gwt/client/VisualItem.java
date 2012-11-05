package org.percepta.mgrankvi.floorplanner.gwt.client;

import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.Widget;

public abstract class VisualItem extends Widget {

	public abstract void paint(final Context2d context);

	public abstract boolean pointInObject(final int x, final int y);

	public Double getArea(final Point p1, final Point p2, final Point p3) {
		return Math.abs((p1.getX() * p2.getY() + p2.getX() * p3.getY() + p3.getX() * p1.getY() - p1.getX() * p3.getY() - p3.getX() * p2.getY() - p2.getX()
				* p1.getY()) / 2.0);
	}

	public int minX(final List<Point> points) {
		int min = Integer.MAX_VALUE;
		for (final Point p : points) {
			if (p.getX() < min) {
				min = p.getX();
			}
		}
		return min;
	}

	public int minY(final List<Point> points) {
		int min = Integer.MAX_VALUE;
		for (final Point p : points) {
			if (p.getY() < min) {
				min = p.getY();
			}
		}
		return min;
	}

	public int maxX(final List<Point> points) {
		int max = Integer.MIN_VALUE;
		for (final Point p : points) {
			if (p.getX() > max) {
				max = p.getX();
			}
		}
		return max;
	}

	public int maxY(final List<Point> points) {
		int max = Integer.MIN_VALUE;
		for (final Point p : points) {
			if (p.getY() > max) {
				max = p.getY();
			}
		}
		return max;
	}

	/* Line segments intersect */
	public boolean isOnSegment(final int xi, final int yi, final int xj, final int yj, final int xk, final int yk) {
		return (xi <= xk || xj <= xk) && (xk <= xi || xk <= xj) && (yi <= yk || yj <= yk) && (yk <= yi || yk <= yj);
	}

	public int computeDirection(final int xi, final int yi, final int xj, final int yj, final int xk, final int yk) {
		final int a = (xk - xi) * (yj - yi);
		final int b = (xj - xi) * (yk - yi);
		return a < b ? -1 : a > b ? 1 : 0;
	}

	public int computeDirection(final Line line, final Point point) {
		final int a = (point.getX() - line.start.getX()) * (line.end.getY() - line.start.getY());
		final int b = (line.end.getX() - line.start.getX()) * (point.getY() - line.start.getY());
		return a < b ? -1 : a > b ? 1 : 0;
	}

	/**
	 * Do line segments (l1.start.getX(), l1.start.getY())--(l1.end.getX(),
	 * l1.end.getY()) and (l2.start.getX(), l2.start.getY())--(l2.end.getX(),
	 * l2.end.getY()) intersect?
	 */
	// public boolean lineSegmentsIntersect(final Line l1, final Line l2) {
	// final int d1 = computeDirection(l2.start.getX(), l2.start.getY(),
	// l2.end.getX(), l2.end.getY(), l1.start.getX(), l1.start.getY());
	// final int d2 = computeDirection(l2.start.getX(), l2.start.getY(),
	// l2.end.getX(), l2.end.getY(), l1.end.getX(), l1.end.getY());
	// final int d3 = computeDirection(l1.start.getX(), l1.start.getY(),
	// l1.end.getX(), l1.end.getY(), l2.start.getX(), l2.start.getY());
	// final int d4 = computeDirection(l1.start.getX(), l1.start.getY(),
	// l1.end.getX(), l1.end.getY(), l2.end.getX(), l2.end.getY());
	// return (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0)
	// || (d3 < 0 && d4 > 0)))
	// || (d1 == 0 && isOnSegment(l2.start.getX(), l2.start.getY(),
	// l2.end.getX(), l2.end.getY(), l1.start.getX(), l1.start.getY()))
	// || (d2 == 0 && isOnSegment(l2.start.getX(), l2.start.getY(),
	// l2.end.getX(), l2.end.getY(), l1.end.getX(), l1.end.getY()))
	// || (d3 == 0 && isOnSegment(l1.start.getX(), l1.start.getY(),
	// l1.end.getX(), l1.end.getY(), l2.start.getX(), l2.start.getY()))
	// || (d4 == 0 && isOnSegment(l1.start.getX(), l1.start.getY(),
	// l1.end.getX(), l1.end.getY(), l2.end.getX(), l2.end.getY()));
	// }

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
