package org.percepta.mgrankvi.floorplanner.gwt.client.room;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.GeometryUtil;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.CLabel;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.ItemUtils;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.Document;

public class CRoom extends VisualItem {

	private boolean selected = false;
	private final List<VisualItem> roomItems = new LinkedList<VisualItem>();

	public CRoom() {
		// dummy element
		setElement(Document.get().createDivElement());
	}

	public CRoom(final String id, final List<Point> points, final Point position) {
		this.points.addAll(points);
		this.position = position;
		this.id = id;
	}

	@Override
	public void setName(final String name) {
		super.setName(name);
		final Point pos = combine(position, getCenter());
		pos.move(-25, 0);
		final CLabel label = new CLabel(name, pos);
		roomItems.add(label);
	}

	public void setPoints(final List<Point> points) {
		this.points.addAll(points);
	}

	public void addRoomItem(final VisualItem item) {
		roomItems.add(item);
	}

	public void removeRoomItem(final VisualItem item) {
		roomItems.remove(item);
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setPosition(final Point position) {
		this.position = position;
	}

	@Override
	public void movePosition(final int x, final int y) {
		super.movePosition(x, y);
		for (final VisualItem item : roomItems) {
			item.movePosition(x, y);
		}
	}

	public void setSelection(final boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	@Override
	public void paint(final Context2d context) {
		if (selected) {
			ItemUtils.paintPointToPoint(context, points, position, CssColor.make("GREEN"));
			ItemUtils.paintPointSelections(context, points, position, CssColor.make("PURPLE"));
		} else {
			ItemUtils.paintPointToPoint(context, points, position, CssColor.make("BLACK"));
		}
		for (final VisualItem item : roomItems) {
			item.paint(context);
		}
	}

	@Override
	public boolean pointInObject(final int x, final int y) {
		if (x < (position.getX() + minX(points)) || x > (position.getX() + maxX(points)) || y < (position.getY() + minY(points))
				|| y > (position.getY() + maxY(points))) {
			return false;
		}

		final Point target = new Point(x, y);
		final Line targetLine = new Line(new Point(position.getX() + minX(points) - 50, position.getY() + minY(points) - 50), target);
		int intercepts = 0;
		for (int i = 0; i + 2 <= points.size(); i++) {
			final Line line = new Line(combine(position, points.get(i)), combine(position, points.get(i + 1)));
			if ((line.start.getX() > x && line.end.getX() > x) || (line.start.getY() > y && line.end.getY() > y)) {
				continue;
			}
			if (lineSegmentsIntersect(line, targetLine)) {
				intercepts++;
			}
		}
		final Line line = new Line(combine(position, points.getFirst()), combine(position, points.getLast()));
		if (lineSegmentsIntersect(line, targetLine)) {
			intercepts++;
		}

		return intercepts % 2 == 1;
	}

	public Point selectedPoint(final int x, final int y) {
		for (final Point point : points) {
			final int pointXMin = position.getX() + point.getX() - 4;
			final int pointYMin = position.getY() + point.getY() - 4;
			if (x > pointXMin && x < pointXMin + 8 && y > pointYMin && y < pointYMin + 8) {
				return point;
			}
		}
		return null;
	}

	private Point combine(final Point p1, final Point p2) {
		return GeometryUtil.combine(p1, p2);
	}
}
