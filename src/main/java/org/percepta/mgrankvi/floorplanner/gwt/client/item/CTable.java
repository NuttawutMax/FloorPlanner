package org.percepta.mgrankvi.floorplanner.gwt.client.item;

import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;

public class CTable extends VisualItem {

	@Override
	public void paint(final Context2d context) {
		paint(context, new Point(0, 0));
	}

	@Override
	public void paint(final Context2d context, final Point offset) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean pointInObject(final int x, final int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
