package org.percepta.mgrankvi.floorplanner.gwt.client.geometry;

import java.io.Serializable;

public class Point implements Serializable {

	private static final long serialVersionUID = -3808768487503038119L;

	private int x;
	private int y;

	public Point() {

	}

	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(final int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + x + " , " + y + "]";
	}

	public void move(final int x, final int y) {
		setX(getX() + x);
		setY(getY() + y);
	}
}
