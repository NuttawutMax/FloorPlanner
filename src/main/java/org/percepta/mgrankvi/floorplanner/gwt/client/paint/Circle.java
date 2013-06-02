package org.percepta.mgrankvi.floorplanner.gwt.client.paint;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

public class Circle {

	Point position;
	double startAngle;
	double endAngle;
	double radius;

	public Circle() {

	}

	public Circle(final Point position, final double startAngle, final double endAngle, final double radius) {
		this.position = position;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.radius = radius;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(final double startAngle) {
		this.startAngle = startAngle;
	}

	public double getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(final double endAngle) {
		this.endAngle = endAngle;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(final double radius) {
		this.radius = radius;
	}

}
