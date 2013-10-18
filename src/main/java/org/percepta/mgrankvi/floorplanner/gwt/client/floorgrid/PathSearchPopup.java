package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.item.table.CTable;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.vaadin.client.ui.VNotification;

public class PathSearchPopup extends PopupPanel {

    CTable fromTable;
    CTable toTable;

    public PathSearchPopup(final List<CFloor> floors, final CGrid grid) {
        final TextBox from = new TextBox();
        final TextBox to = new TextBox();

        final FlowPanel layout = new FlowPanel();
        layout.add(from);
        layout.add(to);

        final Button ok = new Button("Ok", new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {

                fromTable = getSingleName(floors, from.getValue());
                toTable = getSingleName(floors, to.getValue());

                if (fromTable != null && toTable != null) {
                    for (final CFloor floor : floors) {
                        floor.hideNames();
                    }
                    fromTable.setSelected(true);
                    toTable.setSelected(true);
                    grid.getPath(fromTable.getNodeId(), toTable.getNodeId());
                    hide();
                } else if (fromTable == null) {

                    final VNotification notification = new VNotification();
                    final Style style = notification.getElement().getStyle();
                    style.setBackgroundColor("#c8ccd0");
                    style.setPadding(15.0, Unit.PX);
                    style.setProperty("border-radius", "4px");
                    style.setProperty("-moz-border-radius", "4px");
                    style.setProperty("-webkit-border-radius", "4px");

                    notification.show("No user found for [" + from.getValue() + "]", VNotification.BOTTOM_RIGHT, null);
                } else {
                    final VNotification notification = new VNotification();
                    final Style style = notification.getElement().getStyle();
                    style.setBackgroundColor("#c8ccd0");
                    style.setPadding(15.0, Unit.PX);
                    style.setProperty("border-radius", "4px");
                    style.setProperty("-moz-border-radius", "4px");
                    style.setProperty("-webkit-border-radius", "4px");

                    notification.show("No user found for [" + to.getValue() + "]", VNotification.BOTTOM_RIGHT, null);
                }
            }
        });
        ok.getElement().getStyle().setProperty("float", "right");
        final Button cancel = new Button("Cancel", new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                hide();
            }
        });
        cancel.getElement().getStyle().setProperty("float", "right");

        layout.add(ok);
        layout.add(cancel);

        final Style style = layout.getElement().getStyle();
        style.setBackgroundColor("burlywood");
        style.setPadding(10.0, Unit.PX);
        style.setProperty("border-radius", "4px");
        style.setProperty("-moz-border-radius", "4px");
        style.setProperty("-webkit-border-radius", "4px");
        style.setPaddingBottom(25, Unit.PX);

        add(layout);
        setPopupPosition(Window.getClientWidth() / 2, Window.getClientHeight() / 2);
        show();
    }

    public CTable getSingleName(final List<CFloor> floors, final String name) {
        for (final CFloor floor : floors) {
            if (floor.getNames().contains(name)) {
                return floor.getTableOfSelectedPerson(name);
            } else if (floor.namesContain(name)) {
                final LinkedList<String> possible = floor.possibilities(name);
                if (possible.size() == 1) {
                    return floor.getTableOfSelectedPerson(possible.getFirst());
                }
            }
        }
        return null;
    }
}
