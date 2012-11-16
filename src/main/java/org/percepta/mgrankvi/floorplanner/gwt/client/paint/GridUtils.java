package org.percepta.mgrankvi.floorplanner.gwt.client.paint;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Window;

public class GridUtils {

	public static void paintGrid(final Context2d context, final Point offset, final double gridSize, final Point origo) {
		double x = offset.getX() - gridSize;
		double y = offset.getY() - gridSize;
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

	public static void paintZoomInButton(final Context2d context, final Point position, final int size, final String color) {
		final double half = size / 2.0;
		context.setFillStyle(color);
		context.beginPath();

		context.fillRect(position.getX(), position.getY(), size, 2);
		context.fillRect(position.getX(), position.getY(), 2, size);
		context.fillRect(position.getX(), position.getY() + size - 2, size, 2);
		context.fillRect(position.getX() + size - 2, position.getY(), 2, size);

		context.fillRect(position.getX(), position.getY(), half - 2, half - 2);
		context.fillRect(position.getX() + half + 2, position.getY(), half - 2, half - 2);
		context.fillRect(position.getX(), position.getY() + half + 2, half - 2, half - 2);
		context.fillRect(position.getX() + half + 2, position.getY() + half + 2, half - 2, half - 2);

		context.closePath();
		context.fill();
	}

	public static void paintZoomOutButton(final Context2d context, final Point position, final int size, final String color) {
		final double half = size / 2.0;
		context.setFillStyle(color);
		context.beginPath();

		context.fillRect(position.getX(), position.getY(), size, 2);
		context.fillRect(position.getX(), position.getY(), 2, size);
		context.fillRect(position.getX(), position.getY() + size - 2, size, 2);
		context.fillRect(position.getX() + size - 2, position.getY(), 2, size);

		context.fillRect(position.getX(), position.getY(), size, half - 2);
		context.fillRect(position.getX(), position.getY() + half + 2, size, half - 2);

		context.closePath();
		context.fill();
	}
}
