package org.percepta.mgrankvi.floorplanner.gwt.client.paint;

import java.util.LinkedList;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.FillStrokeStyle;

public class ItemUtils {

	public static void paintPointToPoint(final Context2d context, final LinkedList<Point> points, final Point offset, final FillStrokeStyle color) {
		final Point first = points.getFirst();

		context.setStrokeStyle(color);

		context.beginPath();
		context.moveTo(offset.getX() + first.getX(), offset.getY() + first.getY());

		for (final Point point : points) {
			context.lineTo(offset.getX() + point.getX(), offset.getY() + point.getY());
		}
		context.lineTo(offset.getX() + first.getX(), offset.getY() + first.getY());

		context.closePath();
		context.stroke();
	}

	public static void paintPointSelections(final Context2d context, final LinkedList<Point> points, final Point offset, final FillStrokeStyle color) {
		context.setStrokeStyle(color);

		context.beginPath();

		for (final Point point : points) {
			context.rect(offset.getX() + point.getX() - 2, offset.getY() + point.getY() - 2, 5, 5);
		}

		context.closePath();
		context.stroke();
	}
}
