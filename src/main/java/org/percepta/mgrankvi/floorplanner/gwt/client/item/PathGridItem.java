package org.percepta.mgrankvi.floorplanner.gwt.client.item;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.PathPopup;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.DelinkedLink;
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
    private final Map<Integer, Node> idNodes = new HashMap<Integer, Node>();
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

    public int size() {
        return nodes.size();
    }

    public void clearNodes() {
        nodes.clear();
        circles.clear();
        lines.clear();
    }

    public void addNode(final Node node) {
        nodes.add(node);
        // points.add(node.position);
        final Circle circle = new Circle(node.getPosition(), 0, 2 * Math.PI, 10);
        circles.add(circle);
        nodeMap.put(circle, node);
        if (pathPopup != null) {
            pathPopup.addPoint(node.getId(), node.getPosition());
        }
        idNodes.put(node.getId(), node);
    }

    public void addLink(final int nodeId, final DelinkedLink dlLink) {
        final Node from = idNodes.get(nodeId);
        final Node to = idNodes.get(dlLink.target);
        final Link link = new Link(to, dlLink.weight);
        from.getLinks().add(link);
    }

    public void buildLinks() {
        Logger.getLogger("PathGridItem").log(Level.FINE, "Building links for waypoints");
        final List<Node> added = new LinkedList<Node>();
        for (final Node node : nodes) {
            for (final Link link : node.getLinks()) {
                if (!added.contains(link.getTarget())) {
                    lines.add(new Line(node.getPosition(), link.getTarget().getPosition()));
                }
            }
            added.add(node);
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
                pathPopup.addLink(selectedNode, node, link.getWeight() + 1);
                selectedNode.getLinks().remove(link);
                selectedNode.getLinks().add(new Link(link.getTarget(), link.getWeight() + 1));
                selected = null;
                return;
            }
            selectedNode.addConnectedNode(node, 1);
            lines.add(new Line(selectedNode.getPosition(), node.getPosition()));
            if (pathPopup != null) {
                pathPopup.addLink(selectedNode, node, 1);
            }
            selected = null;
        }
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
