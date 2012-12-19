package org.percepta.mgrankvi.floorplanner.gwt.client.item.table;

import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.GeometryUtil;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.ItemUtils;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class CTable extends VisualItem {

	private Point drawPosition = new Point(0, 0);

	public CTable(final List<Point> points, final Point position) {
		this.points.addAll(points);
		this.position = position;
	}

	@Override
	public void paint(final Context2d context) {
		paint(context, new Point(0, 0));
	}

	@Override
	public void paint(final Context2d context, final Point offset) {
		drawPosition = GeometryUtil.combine(position, offset);

		ItemUtils.paintPointToPoint(context, points, drawPosition, CssColor.make("BLACK"));

		if (getName() != null) {
			paintName(context);
		}
	}

	public void paintName(final Context2d context) {
		final Point drawPosition = GeometryUtil.combine(this.drawPosition,
				new Point((int) Math.floor((maxX() - minX()) * 0.25), (int) Math.floor((maxY() - minY()) * 0.4)));

		context.setFont("bold 10px Courier New");

		final int width = (int) Math.ceil(context.measureText(getName()).getWidth());

		context.setFillStyle("GREEN");
		context.beginPath();

		context.arc(drawPosition.getX(), drawPosition.getY() + 10, 10, 0, Math.PI * 2.0, true);
		context.fillRect(drawPosition.getX(), drawPosition.getY(), width, 20);
		context.arc(drawPosition.getX() + width, drawPosition.getY() + 10, 10, 0, Math.PI * 2.0, true);

		context.closePath();
		context.fill();

		context.setFillStyle("WHITE");
		context.beginPath();

		context.fillText(getName(), drawPosition.getX(), drawPosition.getY() + 12);

		context.closePath();
	}

	@Override
	public boolean pointInObject(final double x, final double y) {
		if (points.isEmpty()) {
			return false;
		}

		if (x < (drawPosition.getX() + minX()) || x > (drawPosition.getX() + maxX()) || y < (drawPosition.getY() + minY())
				|| y > (drawPosition.getY() + maxY())) {
			return false;
		}

		final Point target = new Point(x, y);
		// First point clearly outside shape.
		final Line targetLine = new Line(new Point(drawPosition.getX() + minX() - 50, drawPosition.getY() + minY() - 50), target);
		int intercepts = 0;
		for (int i = 0; i + 2 <= points.size(); i++) {
			final Line line = new Line(GeometryUtil.combine(drawPosition, points.get(i)), GeometryUtil.combine(drawPosition, points.get(i + 1)));
			if ((line.start.getX() > x && line.end.getX() > x) || (line.start.getY() > y && line.end.getY() > y)) {
				continue;
			}
			if (lineSegmentsIntersect(line, targetLine)) {
				intercepts++;
			}
		}
		final Line line = new Line(GeometryUtil.combine(drawPosition, points.getFirst()), GeometryUtil.combine(drawPosition, points.getLast()));
		if (lineSegmentsIntersect(line, targetLine)) {
			intercepts++;
		}

		return intercepts % 2 == 1;
	}

}
