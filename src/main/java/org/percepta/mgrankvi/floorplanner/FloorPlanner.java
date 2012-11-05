package org.percepta.mgrankvi.floorplanner;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.visual.entity.RoomType;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class FloorPlanner extends UI {

	@Override
	protected void init(final VaadinRequest request) {
		final CssLayout layout = new CssLayout();
		setContent(layout);

		final FloorGrid grid = new FloorGrid();
		grid.addRoom(RoomType.square());
		grid.addRoom(RoomType.LShape(new Point(200, 200)));
		grid.addRoom(RoomType.triangle(new Point(300, 100)));
		grid.addRoom(RoomType.fivePoints(new Point(100, 400)));
		// grid.addRoom(new Room(new Hexagon(50, 50).getCorners()));

		addComponent(grid);
	}

}
