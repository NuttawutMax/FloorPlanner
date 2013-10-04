package org.percepta.mgrankvi.floorplanner.gwt.client.item.stairs;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.GeometryUtil;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.Circle;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.ItemUtils;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.Document;

public class CStair extends VisualItem {

    private static double TWO_PI = Math.PI * 2.0;

    private Point drawPosition = new Point(0, 0);
    private String color = "BLACK";
    private String tableColor = "TRANSPARENT";
    private int nodeId = -99;

    private Circle circle;
    private List<Line> lines;
    private Double degrees;

    private Circle circleNoZoom;
    private List<Line> linesNoZoom;

    public CStair() {
        // dummy element
        setElement(Document.get().createDivElement());
    }

    public CStair(final List<Point> points, final Point position) {
        this.points.addAll(points);
        this.position = position;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(final int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public void paint(final Context2d context) {
        paint(context, new Point(0, 0));
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public void setTableColor(final String color) {
        tableColor = color;
    }

    public void setCircle(final Circle circle) {
        this.circle = circle;
    }

    public void setLines(final List<Line> lines) {
        this.lines = lines;
    }

    public void setSegmentation(final Double degrees) {
        this.degrees = degrees;
    }

    @Override
    public void paint(final Context2d context, final Point offset) {
        drawPosition = GeometryUtil.combine(position, offset);

        if (!tableColor.equals("TRANSPARENT")) {
            ItemUtils.fillPointToPoint(context, points, drawPosition, CssColor.make(tableColor));
        }

        final CssColor drawColor = CssColor.make(color);
        if (!points.isEmpty()) {
            ItemUtils.paintPointToPoint(context, points, drawPosition, drawColor);
        }
        if (circle != null) {
            if (degrees != null) {
                final double step = degrees * Math.PI / 180;
                final double full = Math.PI * 2;
                double start = circle.getStartAngle();
                double end = circle.getEndAngle();
                if (end < start) {
                    end += full;
                }
                while (start < end) {
                    double endAngle = start + step;
                    if (endAngle > end) {
                        endAngle = end;
                    }
                    ItemUtils.paintCircle(context, new Circle(circle.getPosition(), start, endAngle, circle.getRadius()), drawPosition, drawColor);
                    start += step;
                }

            } else {
                ItemUtils.paintCircle(context, circle, drawPosition, drawColor);
            }
        }

        if (lines != null && !lines.isEmpty()) {
            for (final Line line : lines) {
                ItemUtils.paintLine(context, line, drawPosition, drawColor);
            }
        }
    }

    @Override
    public boolean pointInObject(final double x, final double y) {
        return false;
    }

    @Override
    public void clicked(final double x, final double y) {
        if (pointInObject(x, y)) {
            if (!tableColor.equals("TRANSPARENT")) {
                tableColor = "TRANSPARENT";
            }
        }
    }

    @Override
    public void scale(final double scale) {
        super.scale(scale);
        if (circle != null) {
            circleNoZoom = new Circle(new Point(circle.getPosition().getX(), circle.getPosition().getY()), circle.getStartAngle(), circle.getEndAngle(),
                    circle.getRadius());
            // circle.getPosition().setX((int)
            // Math.ceil(circle.getPosition().getX() * scale));
            // circle.getPosition().setY((int)
            // Math.ceil(circle.getPosition().getY() * scale));
            circle.setRadius(circle.getRadius() * scale);
        }
        if (lines != null) {
            linesNoZoom = new LinkedList<Line>();
            for (final Line line : lines) {
                linesNoZoom.add(new Line(new Point(line.start.getX(), line.start.getY()), new Point(line.end.getX(), line.end.getY())));
                line.start.setX((int) Math.ceil(line.start.getX() * scale));
                line.start.setY((int) Math.ceil(line.start.getY() * scale));

                line.end.setX((int) Math.ceil(line.end.getX() * scale));
                line.end.setY((int) Math.ceil(line.end.getY() * scale));
            }
        }
        for (final Point p : points) {
            p.setX((int) Math.ceil(p.getX() * scale));
            p.setY((int) Math.ceil(p.getY() * scale));
        }

        position.setX((int) Math.ceil(position.getX() * scale));
        position.setY((int) Math.ceil(position.getY() * scale));
    }

    @Override
    public void reset() {
        super.reset();
        if (circle != null) {
            circle = circleNoZoom;
            circleNoZoom = null;
        }

        if (lines != null) {
            lines.clear();
            lines.addAll(linesNoZoom);
            linesNoZoom = null;
        }

    }

}
