package org.percepta.mgrankvi.floorplanner.gwt.client.paint;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Window;

public class GridUtils {

	public static void paintGrid(final Context2d context, final Point offset, final int gridSize, final Point origo) {
		int x = offset.getX() - gridSize;
		int y = offset.getY() - gridSize;
		context.setStrokeStyle("gray");
		context.beginPath();

		do {
			do {
				context.strokeRect(x, y, 1, 1);
				x += gridSize;
			} while (x < Window.getClientWidth());
			y += gridSize;
			x = offset.getX() - gridSize;
		} while (y < Window.getClientHeight());
		context.closePath();
		context.stroke();

		// Mark grid 0,0
		context.setStrokeStyle("RED");
		context.beginPath();

		context.moveTo(origo.getX(), origo.getY() - 4);
		context.lineTo(origo.getX(), origo.getY() + 4);
		context.moveTo(origo.getX() - 4, origo.getY());
		context.lineTo(origo.getX() + 4, origo.getY());

		context.closePath();
		context.stroke();
	}
}
