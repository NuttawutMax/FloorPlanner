package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.buttons;

import com.google.gwt.canvas.dom.client.Context2d;

public abstract class AbstractButton {

	public abstract void paint(final Context2d context, final double progress);

	public abstract void paint(final Context2d context);

	public abstract void hover(final int clientX, final int clientY);

	public abstract void clicked();

	double x = 0;
	double y = 0;
	double offsetX;
	double offsetY;

	public void setX(final double x) {
		if (this.x != x) {
			this.x = x;
		}
	}

	public void setY(final double y) {
		if (this.y != y) {
			this.y = y;
		}
	}
}
