package org.percepta.mgrankvi.floorplanner.gwt.client.info;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
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
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.vaadin.client.VConsole;

public class CInfoEditor extends PopupPanel implements ClickHandler, MouseDownHandler, MouseUpHandler, MouseMoveHandler {

	private static final String CLASSNAME = "c-infoeditor";

	private static final int CANVAS_WIDTH = 350;
	private static final int CANVAS_HEIGHT = 300;

	private final Canvas canvas;
	private final TextBox pointX, pointY, name;
	private final Label xLabel, yLabel, nameLabel;
	private final Button close;

	private final CRoom room;
	private final Point offset;
	private Point select;

	private boolean mouseDown = false;
	private boolean click = true;
	private int downX, downY;

	public CInfoEditor(final CRoom room) {
		this.room = room;
		offset = new Point((CANVAS_WIDTH - (room.maxX() - room.minX())) / 2, (CANVAS_HEIGHT - (room.maxY() - room.minY())) / 2);

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
			content.add(canvas, 0, 50);
			paint();
		} else {
			getElement().setInnerHTML("Canvas not supported");
		}

		xLabel = new Label("X position");
		pointX = new TextBox();
		content.add(xLabel, 400, 100);
		content.add(pointX, 475, 97);

		yLabel = new Label("Y position");
		pointY = new TextBox();
		content.add(yLabel, 400, 125);
		content.add(pointY, 475, 122);

		nameLabel = new Label("Name");
		name = new TextBox();
		name.setText(room.getName());
		content.add(nameLabel, 400, 75);
		content.add(name, 475, 72);

		close = new Button("X");
		close.setWidth("25px");
		close.setHeight("25px");
		content.add(close, 670, 5);

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
				pointX.setValue(Integer.toString(select.getX()));
				pointY.setValue(Integer.toString(select.getY()));
			}
		} else if (close.getElement().equals(event.getNativeEvent().getEventTarget())) {
			hide();
		}
		click = true;
	}

	private Point getSelectPoint(final int x, final int y) {
		VConsole.log(x + "," + y);
		for (final Point point : room.getPoints()) {
			final int pointXMin = point.getX() - 4;
			final int pointYMin = point.getY() - 4;

			if (x > pointXMin && x < pointXMin + 8 && y > pointYMin && y < pointYMin + 8) {
				return point;
			}
		}
		return null;
	}
}
