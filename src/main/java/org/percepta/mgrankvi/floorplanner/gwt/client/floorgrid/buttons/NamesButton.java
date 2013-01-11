package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.buttons;

import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.ButtonBar;

import com.google.gwt.canvas.dom.client.Context2d;
import com.vaadin.client.VConsole;

public class NamesButton extends AbstractButton {

	private final ButtonBar bar;

	String fillStyle = "GREEN";

	public NamesButton(final ButtonBar bar, final double offsetX, final double offsetY) {
		this.bar = bar;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public void setFillstyle(final String style) {
		fillStyle = style;
	}

	@Override
	public void paint(final Context2d context, final double progress) {
		context.save();

		context.setFillStyle(fillStyle);

		context.beginPath();

		final double size = 20 * progress;
		// final double halfSize = 10 * progress;
		context.fillRect(x - offsetX, y - offsetY, size, size);

		context.closePath();
		context.stroke();

		context.restore();
	}

	@Override
	public void paint(final Context2d context) {
		context.save();

		context.setFillStyle(fillStyle);

		context.beginPath();

		context.fillRect(x - offsetX, y - offsetY, 20, 20);

		context.closePath();
		context.stroke();

		context.setStrokeStyle("white");
		context.setFillStyle("white");
		context.beginPath();
		context.arc(x - offsetX + 10, y - offsetY + 10, 7, 0, Math.PI * 2, true);
		context.fillText("N", x - offsetX + 6, y - offsetY + 13);
		context.closePath();
		context.stroke();

		context.restore();
	}

	@Override
	public void hover(final int clientX, final int clientY) {
		if (inside(clientX, clientY)) {
			fillStyle = "ORANGE";
		} else {
			fillStyle = "GREEN";
		}
	}

	@Override
	public void clicked() {
		if (fillStyle.equals("ORANGE")) {
			bar.showNames();
		}

	}

	private boolean inside(final int pointX, final int pointY) {
		VConsole.log(" -- X: " + pointX + " Y: " + pointY);
		VConsole.log(" -- X min/max: " + (x - offsetX) + " / " + (x - offsetX + 20));
		VConsole.log(" -- Y min/max: " + (y - offsetY) + " / " + (y - offsetY + 20));
		if (pointX > x - offsetX && pointX < x - offsetX + 20 && pointY > y - offsetY && pointY < y - offsetY + 20) {
			return true;
		}
		return false;
	}
}
