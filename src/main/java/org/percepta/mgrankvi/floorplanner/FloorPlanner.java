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
		// grid.addRoom(RoomType.square(new Point()));

		// Tiina
		grid.addRoom(RoomType.customRoom("Tiina Wasberg", new Point(0, 0), new Point(0, 0), new Point(105, 0), new Point(105, 20), new Point(90, 50),
				new Point(90, 150), new Point(0, 150)));
		// Vappula
		grid.addRoom(RoomType.customRoom("Mikael Vappula", new Point(90, 0), new Point(15, 0), new Point(65, 0), new Point(65, 20), new Point(80, 50),
				new Point(80, 120), new Point(0, 120), new Point(0, 50), new Point(15, 20)));
		// Muurimaa
		grid.addRoom(RoomType.customRoom("Henri Muurimaa", new Point(155, 0), new Point(0, 0), new Point(105, 0), new Point(105, 150), new Point(15, 150),
				new Point(15, 50), new Point(0, 20)));
		// GuitarHero
		grid.addRoom(RoomType.square("Guitar Hero", new Point(35, 150)));
		// PikkuNeukkari
		grid.addRoom(RoomType.square("Pikku Neukkari", new Point(35, 250)));
		// Kitchen
		grid.addRoom(RoomType.customRoom("Kitchen", new Point(0, 350), new Point(0, 5), new Point(35, 5), new Point(35, 0), new Point(135, 0), new Point(135,
				100), new Point(0, 100)));
		// Lounge
		grid.addRoom(RoomType.customRoom("Lounge", new Point(135, 150), new Point(0, 0), new Point(125, 0), new Point(125, 300), new Point(0, 300)));

		// MainHall width: 560 height: 520, toilet: w80, h100
		grid.addRoom(RoomType.customRoom("Main Hall", new Point(-150, 450), new Point(0, 0), new Point(560, 0), new Point(560, 180), new Point(480, 180),
				new Point(480, 280), new Point(560, 280), new Point(560, 520), new Point(0, 520), new Point(0, 280), new Point(80, 280), new Point(80, 180),
				new Point(0, 180)));

		// 140, 130
		grid.addRoom(RoomType.customRoom(new Point(-140, 970), new Point(0, 0), new Point(140, 0), new Point(140, 130), new Point(0, 130)));

		// MetsäNeukkari
		grid.addRoom(RoomType.customRoom("Metsä neukkari", new Point(0, 970), new Point(0, 0), new Point(160, 0), new Point(160, 130), new Point(0, 130)));

		// StuffRomm
		grid.addRoom(RoomType.customRoom("Storage", new Point(160, 970), new Point(0, 0), new Point(70, 0), new Point(70, 130), new Point(0, 130)));
		// ServerRoom
		grid.addRoom(RoomType.customRoom("Server room", new Point(230, 970), new Point(0, 0), new Point(50, 0), new Point(50, 130), new Point(0, 130)));

		// grid.addRoom(RoomType.LShape(new Point(200, 200)));
		// grid.addRoom(RoomType.triangle(new Point(300, 100)));
		// grid.addRoom(RoomType.fivePoints(new Point(100, 400)));
		// grid.addRoom(new Room(new Hexagon(50, 50).getCorners()));

		addComponent(grid);
	}
}
