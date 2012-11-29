package org.percepta.mgrankvi.floorplanner.gwt.client.geometry;

import java.util.List;

public class GeometryUtil {

	public Double getArea(final Point p1, final Point p2, final Point p3) {
		return Math.abs((p1.getX() * p2.getY() + p2.getX() * p3.getY() + p3.getX() * p1.getY() - p1.getX() * p3.getY() - p3.getX() * p2.getY() - p2.getX()
				* p1.getY()) / 2.0);
	}

	public static Double getDirectionalCoefficient(final Point point1, final Point point2) {

		return (1.0 * (point2.getY() - point1.getY())) / (point2.getX() - point1.getX());
	}

	public static int validateAndCorrectHexProportions(final int width, int height) {
		final int divide = (int) ((1.0 * width / height) * 100);
		if (divide != 57 || divide != 58) {
			height = (int) (width / 0.58);
		}
		return height;
	}

	public static double distance(final Point p1, final Point p2) {
		final int x_diff = p2.getX() - p1.getX();
		final int y_diff = p2.getY() - p1.getY();

		return Math.sqrt(x_diff * x_diff + y_diff * y_diff);
	}

	public static Point combine(final Point p1, final Point p2) {
		return new Point(p1.getX() + p2.getX(), p1.getY() + p2.getY());
	}

	public static int minX(final List<Point> points) {
		int min = Integer.MAX_VALUE;
		for (final Point p : points) {
			if (p.getX() < min) {
				min = p.getX();
			}
		}
		return min;
	}

	public static int minY(final List<Point> points) {
		int min = Integer.MAX_VALUE;
		for (final Point p : points) {
			if (p.getY() < min) {
				min = p.getY();
			}
		}
		return min;
	}

	public static int maxX(final List<Point> points) {
		int max = Integer.MIN_VALUE;
		for (final Point p : points) {
			if (p.getX() > max) {
				max = p.getX();
			}
		}
		return max;
	}

	public static int maxY(final List<Point> points) {
		int max = Integer.MIN_VALUE;
		for (final Point p : points) {
			if (p.getY() > max) {
				max = p.getY();
			}
		}
		return max;
	}
}
