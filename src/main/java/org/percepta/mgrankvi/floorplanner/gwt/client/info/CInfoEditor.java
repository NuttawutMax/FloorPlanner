package org.percepta.mgrankvi.floorplanner.gwt.client.info;

import java.util.LinkedList;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.GridUtils;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.ItemUtils;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.CRoom;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
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

public class CInfoEditor extends PopupPanel implements ClickHandler, MouseDownHandler, MouseUpHandler, MouseMoveHandler {

	private static final String CLASSNAME = "c-infoeditor";

	private final Canvas canvas;
	private final TextBox pointX, pointY;
	private final Label xLabel, yLabel;
	private final Button close;

	private final CRoom room;
	private final Point offset;
	private Point select;

	private boolean mouseDown = false;
	private boolean click = true;
	private int downX, downY;

	public CInfoEditor(final CRoom room) {
		this.room = room;
		offset = new Point((300 - (room.maxX() - room.minX())) / 2, (200 - (room.maxY() - room.minY())) / 2);

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
			canvas.setCoordinateSpaceWidth(300);
			canvas.setCoordinateSpaceHeight(200);
			content.add(canvas, 50, 100);
			paint();
		} else {
			getElement().setInnerHTML("Canvas not supported");
		}

		xLabel = new Label("X position");
		yLabel = new Label("Y position");
		pointX = new TextBox();
		pointY = new TextBox();
		content.add(xLabel, 400, 100);
		content.add(yLabel, 400, 125);
		content.add(pointX, 475, 97);
		content.add(pointY, 475, 122);

		close = new Button("X");
		close.setWidth("25px");
		close.setHeight("25px");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (select != null) {
					select.setX(Integer.parseInt(pointX.getValue()));
					select.setY(Integer.parseInt(pointY.getValue()));
				}
				hide();
			}
		});
		content.add(close, 670, 5);

		getElement().appendChild(content.getElement());
	}

	private void paint() {

		final Context2d context = canvas.getContext2d();
		context.beginPath();
		context.rect(0, 0, 299, 199);
		context.closePath();
		context.stroke();

		GridUtils.paintGrid(context, new Point(0, 0), 50, new Point(0, 0));

		ItemUtils.paintPointToPoint(context, (LinkedList<Point>) room.getPoints(), offset, CssColor.make("BLACK"));
		ItemUtils.paintPointSelections(context, (LinkedList<Point>) room.getPoints(), offset, CssColor.make("BLACK"));
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
		if (click) {
			select = getSelectPoint(event.getClientX() - getPopupLeft() - offset.getX() - 50, event.getClientY() - offset.getY() - 100 - getPopupTop());
			if (select != null) {
				pointX.setValue(Integer.toString(select.getX()));
				pointY.setValue(Integer.toString(select.getY()));
			}
		}
		click = true;
	}

	private Point getSelectPoint(final int x, final int y) {
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
