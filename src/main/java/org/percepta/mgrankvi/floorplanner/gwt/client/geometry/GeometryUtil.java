package org.percepta.mgrankvi.floorplanner.gwt.client.geometry;

public class GeometryUtil {

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
}
