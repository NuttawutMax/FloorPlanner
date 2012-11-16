package org.percepta.mgrankvi.floorplanner.gwt.client.item;

import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;

public class CLabel extends VisualItem {

	public CLabel(final String name) {
		setName(name);
	}

	public CLabel(final String name, final Point position) {
		setName(name);
		setPosition(position);
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

	@Override
	public void paint(final Context2d context) {
		context.setFillStyle("GREEN");
		context.beginPath();

		context.arc(position.getX() + 10, position.getY() + 10, 10, 0, Math.PI * 2.0, true);
		context.fillRect(position.getX() + 10, position.getY(), 50, 20);
		context.arc(position.getX() + 60, position.getY() + 10, 10, 0, Math.PI * 2.0, true);

		context.closePath();
		context.fill();

		context.setFillStyle("WHITE");
		context.beginPath();
		context.setFont("bold 12px Monospaced");
		context.fillText(getName(), position.getX() + 10, position.getY() + 12);
		context.closePath();
	}

	@Override
	public boolean pointInObject(final int x, final int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
