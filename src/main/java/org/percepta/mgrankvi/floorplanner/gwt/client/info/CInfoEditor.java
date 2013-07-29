package org.percepta.mgrankvi.floorplanner.gwt.client.info;

import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.CFloor;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.table.CTable;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.GridUtils;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.CRoom;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.vaadin.client.VConsole;

public class CInfoEditor extends PopupPanel implements ClickHandler, MouseDownHandler, MouseUpHandler, MouseMoveHandler {

    private static final String CLASSNAME = "c-infoeditor";

    private static final int CANVAS_WIDTH = 350;
    private static final int CANVAS_HEIGHT = 400;

    private final Canvas canvas;
    private final TextBox pointX, pointY, name;
    private final Label xLabel, yLabel, nameLabel;
    private final Button close, find;
    private final ListBox list;

    private final CRoom orgRoom;
    private final CRoom room;
    private final CFloor floor;
    private final Point offset;
    private Point select;

    private boolean mouseDown = false;
    private boolean click = true;
    private int downX, downY;

    public CInfoEditor(final CFloor floor, final CRoom room) {
        this.floor = floor;
        orgRoom = room;
        this.room = room.cloneRoom();
        while (CANVAS_WIDTH - 20 < (this.room.maxX() - this.room.minX()) && CANVAS_HEIGHT - 20 < (this.room.maxY() - this.room.minY())) {
            this.room.scale(0.5);
            this.room.pointMoved();
        }
        offset = new Point((CANVAS_WIDTH - (this.room.maxX() - this.room.minX())) / 2, (CANVAS_HEIGHT - (this.room.maxY() - this.room.minY())) / 2);

        addDomHandler(this, ClickEvent.getType());
        addDomHandler(this, MouseDownEvent.getType());
        addDomHandler(this, MouseMoveEvent.getType());
        addDomHandler(this, MouseUpEvent.getType());

        final Style style = getElement().getStyle();
        style.setProperty("backgroundColor", "whiteSmoke");
        style.setProperty("borderColor", "black");
        style.setProperty("borderWidth", "1px 1px 1px 1px");
        style.setProperty("borderStyle", "solid");

        setStyleName(CLASSNAME);

        final AbsolutePanel content = new AbsolutePanel();
        content.setWidth("700px");
        content.setHeight("400px");

        setAutoHideEnabled(true);

        canvas = Canvas.createIfSupported();
        if (canvas != null) {
            canvas.setCoordinateSpaceWidth(CANVAS_WIDTH);
            canvas.setCoordinateSpaceHeight(CANVAS_HEIGHT);
            content.add(canvas, 0, 0);
            paint();
        } else {
            getElement().setInnerHTML("Canvas not supported");
        }

        xLabel = new Label("X position");
        pointX = new TextBox();
        pointX.setWidth("125px");
        content.add(xLabel, 400, 100);
        content.add(pointX, 475, 97);

        yLabel = new Label("Y position");
        pointY = new TextBox();
        pointY.setWidth("125px");
        content.add(yLabel, 400, 125);
        content.add(pointY, 475, 122);

        nameLabel = new Label("Name");
        name = new TextBox();
        name.setWidth("125px");
        name.setText(room.getName());
        content.add(nameLabel, 400, 75);
        content.add(name, 475, 72);

        close = new Button("X");
        close.setWidth("25px");
        close.setHeight("25px");
        content.add(close, 670, 5);

        list = new ListBox();
        list.setWidth("225px");
        for (final VisualItem item : orgRoom.getRoomItems()) {
            if (item instanceof CTable) {
                final String name = ((CTable) item).getName();
                if (name != null && !name.isEmpty()) {
                    list.addItem(name);
                }
            }
        }
        list.setVisibleItemCount(5);
        content.add(new Label("People in room:"), 400, 150);
        content.add(list, 425, 175);

        find = new Button("Show");
        content.add(find, 555, 148);

        getElement().appendChild(content.getElement());
    }

    private void paint() {

        // final LinkedList<Point> points = (LinkedList<Point>)
        // room.getPoints();

        final Context2d context = canvas.getContext2d();
        context.beginPath();
        context.rect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        context.closePath();
        context.stroke();

        GridUtils.paintGrid(context, new Point(0, 0), 50, offset);
        room.paint(context, offset);
        // ItemUtils.paintPointToPoint(context, points, offset,
        // CssColor.make("BLACK"));
        // ItemUtils.paintPointSelections(context, points, offset,
        // CssColor.make("BLACK"));

        if (select != null) {
            context.setStrokeStyle("LIMEGREEN");

            context.beginPath();

            context.rect(offset.getX() + select.getX() - 2, offset.getY() + select.getY() - 2, 5, 5);

            context.closePath();
            context.stroke();
        }
    }

    @Override
    public void onMouseMove(final MouseMoveEvent event) {
        if (mouseDown) {
            click = false;
            final int offsetY = event.getClientY() - downY;
            final int offsetX = event.getClientX() - downX;

            setPopupPosition(getPopupLeft() + offsetX, getPopupTop() + offsetY);

            downX = event.getClientX();
            downY = event.getClientY();
        }
    }

    @Override
    public void onMouseUp(final MouseUpEvent event) {
        mouseDown = false;
    }

    @Override
    public void onMouseDown(final MouseDownEvent event) {
        mouseDown = true;
        downX = event.getClientX();
        downY = event.getClientY();
    }

    @Override
    public void onClick(final ClickEvent event) {
        if (click && canvas.getElement().equals(event.getNativeEvent().getEventTarget())) {
            select = getSelectPoint(event.getClientX() - getPopupLeft() - offset.getX(), event.getClientY() - offset.getY() - 50 - getPopupTop());
            if (select != null) {
                pointX.setValue(Double.toString(select.getX()));
                pointY.setValue(Double.toString(select.getY()));
            }
        } else if (close.getElement().equals(event.getNativeEvent().getEventTarget())) {
            hide();
        } else if (find.getElement().equals(event.getNativeEvent().getEventTarget())) {
            VConsole.log("Button click");
            floor.markTableOfSelectedPerson(list.getValue(list.getSelectedIndex()));
            CInfoEditor.this.hide();
        }
        click = true;
    }

    private Point getSelectPoint(final double x, final double y) {
        VConsole.log(x + "," + y);
        for (final Point point : room.getPoints()) {
            final double pointXMin = point.getX() - 4;
            final double pointYMin = point.getY() - 4;

            if (x > pointXMin && x < pointXMin + 8 && y > pointYMin && y < pointYMin + 8) {
                return point;
            }
        }
        return null;
    }
}
