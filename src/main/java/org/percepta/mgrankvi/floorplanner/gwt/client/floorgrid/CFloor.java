package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.percepta.mgrankvi.floorplanner.gwt.client.CommandObject;
import org.percepta.mgrankvi.floorplanner.gwt.client.InfoButton;
import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.info.CInfoEditor;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.table.CTable;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.CRoom;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.VConsole;

public class CFloor extends Widget {

	private final List<CRoom> rooms = new LinkedList<CRoom>();

	private CTable markedTable;

	boolean showNames = false;

	private InfoButton hoverElement = null;

	String id;
	int level;

	CRoom selected = null;
	Point targetPoint = null;
	CGrid grid;

	public CFloor() {
		// Dummy
		setElement(Document.get().createDivElement());
		VConsole.log(" --- Created CFloor");
	}

	protected Set<String> getNames() {
		final Set<String> names = new HashSet<String>();
		for (final CRoom room : rooms) {
			for (final VisualItem item : room.getRoomItems()) {
				if (item instanceof CTable) {
					names.add(item.getName());
				}
			}
		}
		return new HashSet<String>(names);
	}

	public List<CRoom> getRooms() {
		return new LinkedList<CRoom>(rooms);
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void paint() {
		for (final CRoom room : rooms) {
			room.paint(grid.canvas.getContext2d());
		}
		if (hoverElement != null) {
			hoverElement.paint(grid.canvas.getContext2d());
		}
	}

	// Mouse event handlers
	public void click(final double clientX, final double clientY) {
		if (hoverElement != null && hoverElement.pointInObject(clientX, clientY)) {
			// fireEvent(new
			// MenuEvent(MenuEvent.MenuEventType.OPEN_ROOM_INFO,
			// hoverElement.getRoom().getId()));
			final CInfoEditor info = new CInfoEditor(this, hoverElement.getRoom());
			info.setPopupPosition((Window.getClientWidth() / 2) - 350, (Window.getClientHeight() / 2) - 200);
			info.show();
		}
	}

	public void clickEditable(final double clientX, final double clientY) {

		boolean selected = false;
		for (final CRoom room : rooms) {
			if (room.pointInObject(clientX, clientY) && !selected) {
				room.setSelection(true);
				selected = true;
			} else {
				room.setSelection(false);
			}
		}
	}

	public void clickForRoomSelect(final double downX, final double downY) {
		for (final CRoom room : rooms) {
			room.clicked(downX, downY);
		}
	}

	public void scale(final double scale) {
		for (final CRoom room : rooms) {
			room.scale(scale);
		}
	}

	public void reset() {
		for (final CRoom room : rooms) {
			room.reset();
		}
	}

	public void mouseDownEditable(final int downX, final int downY) {
		for (final CRoom room : rooms) {
			if (room.isSelected()) {
				targetPoint = room.selectedPoint(downX, downY);
				if (targetPoint != null || room.pointInObject(downX, downY)) {
					selected = room;
					break;
				}
			}
			room.clicked(downX, downY);
		}
	}

	public void mouseUp() {
		selected = null;
	}

	public void checkHover(final double clientX, final double clientY) {
		if (hoverElement == null) {
			for (final CRoom room : rooms) {
				if (room.pointInObject(clientX, clientY)) {
					hoverElement = new InfoButton(room);
					break;
				}
			}
		} else {
			if (!hoverElement.getRoom().pointInObject(clientX, clientY)) {
				hoverElement = null;
			}
		}
	}

	// Moving things around

	protected void moveRoom(final CRoom room, final MouseMoveEvent event, final int downX, final int downY) {
		if (targetPoint == null) {
			if (event.isAltKeyDown()) {
			}
			room.movePosition(event.getClientX() - downX, event.getClientY() - downY);
		} else {
			room.pointMoved();
			targetPoint.move(event.getClientX() - downX, event.getClientY() - downY);
		}
	}

	public void panRooms(final int amountx, final int amounty) {
		for (final CRoom room : rooms) {
			room.movePosition(amountx, amounty);
		}
	}

	// stuff
	protected boolean namesContain(final CommandObject cmd) {
		for (final CRoom room : rooms) {
			for (final VisualItem item : room.getRoomItems()) {
				if (item instanceof CTable) {
					if (item.getName() != null && item.getName().toLowerCase().contains(cmd.getValue().toLowerCase())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	protected LinkedList<String> possibilities(final CommandObject cmd) {
		final LinkedList<String> possible = new LinkedList<String>();

		for (final CRoom room : rooms) {
			for (final VisualItem item : room.getRoomItems()) {
				if (item instanceof CTable) {
					if (item.getName() != null && item.getName().toLowerCase().contains(cmd.getValue().toLowerCase())) {
						possible.add(item.getName());
					}
				}
			}
		}

		Collections.sort(possible);

		return possible;
	}

	public void markTableOfSelectedPerson(final String nameOfSelection) {
		if (markedTable != null) {
			markedTable.setTableColor("TRANSPARENT");
			markedTable.setSelected(false);
		}
		for (final CRoom room : rooms) {
			for (final VisualItem item : room.getRoomItems()) {
				if (item instanceof CTable) {
					final CTable table = (CTable) item;
					if (nameOfSelection.equals(table.getName())) {
						markedTable = table;
						table.setTableColor("burlywood");
						table.setSelected(true);
						moveTableToView(room, table);

						return;
					}
				}
			}
		}
	}

	private void moveTableToView(final CRoom room, final CTable table) {
		final double xPointInCanvas = (grid.canvas.getCoordinateSpaceWidth() / 2) - (table.maxX() - table.minX()) / 2;
		final double yPointInCanvas = (grid.canvas.getCoordinateSpaceHeight() / 2) - (table.maxY() - table.minY()) / 2;

		final double tableCornerX = table.getPositionX() + room.getPositionX();
		final double tableCornerY = table.getPositionY() + room.getPositionY();

		final double panX = xPointInCanvas - tableCornerX;
		final double panY = yPointInCanvas - tableCornerY;

		final Animation animate = new Animation() {
			double movedX = 0;
			double movedY = 0;

			@Override
			protected void onUpdate(final double progress) {
				final double moveX = panX * progress - movedX;
				final double moveY = panY * progress - movedY;
				movedX += moveX;
				movedY += moveY;
				grid.pan((int) Math.floor(moveX), (int) Math.floor(moveY));
				grid.repaint();
			}

			@Override
			protected void onComplete() {
				super.onComplete();
				grid.animating = false;
			}
		};
		grid.animating = true;
		VConsole.log(" -- X: " + panX + " Y: " + panY);
		animate.run(Math.abs(panX) > Math.abs(panY) ? (int) (Math.abs(panX)) : (int) (Math.abs(panY)));
	}

	public void remove(final CRoom selectedRoom) {
		rooms.remove(selectedRoom);
	}

	public void showNames() {
		showNames = !showNames;
		for (final CRoom room : rooms) {
			for (final VisualItem item : room.getRoomItems()) {
				if (item instanceof CTable) {
					((CTable) item).setSelected(showNames);
				}
			}
		}
	}

	protected void checkForRemoveAndAddItem(final MenuBar rootMenu) {
		CRoom selectedRoom = null;
		for (final CRoom room : rooms) {
			if (room.isSelected()) {
				selectedRoom = room;
				break;
			}
		}
		if (selectedRoom != null) {
			final MenuItem remove = new MenuItem("Remove selected", new RemoveCommand(selectedRoom));
			rootMenu.addItem(remove);
		}
	}

	public HandlerRegistration addMenuEventHandler(final MenuEventHandler menuEventHandler) {
		return addHandler(menuEventHandler, MenuEvent.getType());
	}

	public class AddCommand implements Command {

		private final int corners, x, y;

		public AddCommand(final int corners, final int x, final int y) {
			this.corners = corners;
			this.x = x;
			this.y = y;
		}

		@Override
		public void execute() {
			fireEvent(new MenuEvent(MenuEvent.MenuEventType.ADD_ROOM, corners, x, y));
			grid.contextMenu.hide();
			grid.contextMenu = null;
		}
	}

	public class RemoveCommand implements Command {
		private final CRoom selectedRoom;

		public RemoveCommand(final CRoom selectedRoom) {
			this.selectedRoom = selectedRoom;
		}

		@Override
		public void execute() {
			remove(selectedRoom);
			fireEvent(new MenuEvent(MenuEvent.MenuEventType.REMOVE_ROOM, selectedRoom));
			grid.contextMenu.hide();
			grid.contextMenu = null;
			grid.repaint();
		}
	}

	public ScheduledCommand addCommand(final int i, final int x, final int y) {
		return new AddCommand(i, x, y);
	}

	public void clear() {
		rooms.clear();
	}

	public void add(final Widget widget) {
		if (widget instanceof CRoom) {
			final CRoom room = (CRoom) widget;
			rooms.add(room);
		}
	}
}
