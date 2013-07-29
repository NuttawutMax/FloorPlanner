package org.percepta.mgrankvi.floorplanner.gwt.client.item;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.PathPopup;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.GeometryUtil;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Line;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Link;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Node;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.Circle;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.ItemUtils;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class PathGridItem extends CItem {

    private final List<Node> nodes = new LinkedList<Node>();
    private final Map<Circle, Node> nodeMap = new HashMap<Circle, Node>();

    private Circle selected;
    private static final String COLOR = "BLACK";
    private static final String SELECTED_COLOR = "GREEN";

    private PathPopup pathPopup;

    public PathGridItem() {
    }

    public PathGridItem(final PathPopup pathPopup) {
        this.pathPopup = pathPopup;
    }

    public void addNode(final Node node) {
        nodes.add(node);
        // points.add(node.position);
        final Circle circle = new Circle(node.position, 0, 2 * Math.PI, 10);
        circles.add(circle);
        nodeMap.put(circle, node);
        if (pathPopup != null) {
            pathPopup.addPoint(node.id, node.position);
        }
    }

    public boolean hasSelected() {
        return selected != null;
    }

    public void selectNode(final double x, final double y) {
        for (final Circle circle : circles) {
            if (circle.pointInCircle(new Point(x, y))) {
                selected = circle;
            }
        }
    }

    public void link(final double x, final double y) {
        Node node = null;
        for (final Circle circle : circles) {
            if (circle.pointInCircle(new Point(x, y))) {
                if (selected.equals(circle)) {
                    selected = null;
                    return;
                }
                node = nodeMap.get(circle);
            }
        }
        if (node != null) {
            final Node selectedNode = nodeMap.get(selected);
            if (selectedNode.hasLinkTo(node)) {
                pathPopup.removeLink(selectedNode, node);
                final Link link = selectedNode.getLinkTo(node);
                pathPopup.addLink(selectedNode, node, link.weight + 1);
                selectedNode.links.remove(link);
                selectedNode.links.add(new Link(link.target, link.weight + 1));
                return;
            }
            selectedNode.addConnectedNode(node, 1);
            lines.add(new Line(selectedNode.position, node.position));
            if (pathPopup != null) {
                pathPopup.addLink(selectedNode, node, 1);
            }
        }
        selected = null;
    }

    @Override
    public void paint(final Context2d context, final Point offset) {
        drawPosition = GeometryUtil.combine(position, offset);

        for (final Circle circle : circles) {
            if (circle.equals(selected)) {
                ItemUtils.paintCircle(context, circle, drawPosition, CssColor.make(SELECTED_COLOR));
            } else {
                ItemUtils.paintCircle(context, circle, drawPosition, CssColor.make(COLOR));
            }
        }
        for (final Line line : lines) {
            ItemUtils.paintLine(context, line, drawPosition, CssColor.make(COLOR));
        }
    }

    @Override
    public boolean pointInObject(final double x, final double y) {
        for (final Circle circle : circles) {
            if (circle.pointInCircle(new Point(x, y))) {
                return true;
            }
        }
        return false;
    }
}
