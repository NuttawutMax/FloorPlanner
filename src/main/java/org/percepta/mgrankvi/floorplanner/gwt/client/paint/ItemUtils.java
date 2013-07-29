package org.percepta.mgrankvi.floorplanner.gwt.client.paint;

import java.util.LinkedList;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.FillStrokeStyle;

public class ItemUtils {

    public static void fillPointToPoint(final Context2d context, final LinkedList<Point> points, final Point offset, final FillStrokeStyle color) {
        final Point first = points.getFirst();

        context.setFillStyle(color);

        context.beginPath();
        context.moveTo(offset.getX() + first.getX(), offset.getY() + first.getY());

        for (final Point point : points) {
            context.lineTo(offset.getX() + point.getX(), offset.getY() + point.getY());
        }
        context.lineTo(offset.getX() + first.getX(), offset.getY() + first.getY());

        context.closePath();
        context.fill();
    }

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

    public static void paintCircle(final Context2d context, final Circle circle, final Point offset, final FillStrokeStyle color) {
        final Point center = circle.getPosition();

        context.setStrokeStyle(color);

        context.beginPath();
        context.moveTo(offset.getX() + center.getX(), offset.getY() + center.getY());

        context.arc(offset.getX() + center.getX(), offset.getY() + center.getY(), circle.getRadius(), circle.getStartAngle(), circle.getEndAngle());

        context.closePath();
        context.stroke();
    }

    public static void paintLine(final Context2d context, final Line line, final Point offset, final FillStrokeStyle color) {
        context.setStrokeStyle(color);

        context.beginPath();
        context.moveTo(offset.getX() + line.start.getX(), offset.getY() + line.start.getY());

        context.lineTo(offset.getX() + line.end.getX(), offset.getY() + line.end.getY());

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
