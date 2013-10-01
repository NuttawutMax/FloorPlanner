package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.HashMap;
import java.util.Map;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Node;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class GeneratedCodePopup extends PopupPanel {

    final ScrollPanel layout = new ScrollPanel();
    FlowPanel flow = new FlowPanel();

    Map<String, Label> linkLabels = new HashMap<String, Label>();

    public GeneratedCodePopup() {
        layout.setWidth("400px");
        layout.setHeight("200px");
        layout.add(flow);

        final Style style = layout.getElement().getStyle();
        style.setBackgroundColor("burlywood");
        style.setPadding(5.0, Unit.PX);
        style.setProperty("border-radius", "4px");
        style.setProperty("-moz-border-radius", "4px");
        style.setProperty("-webkit-border-radius", "4px");

        add(layout);

        show();
    }

    public void addPoint(final int id, final Point point) {
        flow.add(new Label("Node node" + id + " = new Node(" + id + ", new Point(" + point.getX() + "," + point.getY() + "));"));
    }

    public void addLink(final Node node1, final Node node2, final int weight) {
        final Label linkLabel = new Label("node" + node1.getId() + ".addConnectedNode(node" + node2.getId() + ", " + weight + ");");
        flow.add(linkLabel);
        linkLabels.put(node1.getId() + ":" + node2.getId(), linkLabel);
    }

    public void removeLink(final Node node1, final Node node2) {
        final Label link = linkLabels.get(node1.getId() + ":" + node2.getId());
        linkLabels.remove(node1.getId() + ":" + node2.getId());
        flow.remove(link);
    }

    public void addLabel(final String content) {
        flow.add(new Label(content));
    }
}
