package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.percepta.mgrankvi.floorplanner.gwt.client.CommandObject;
import org.percepta.mgrankvi.floorplanner.gwt.client.InfoButton;
import org.percepta.mgrankvi.floorplanner.gwt.client.VisualItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.GeometryUtil;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.info.CInfoEditor;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemState;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.door.DoorState;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.table.CItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.table.CTable;
import org.percepta.mgrankvi.floorplanner.gwt.client.paint.GridUtils;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.CRoom;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.RoomState;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.vaadin.client.VConsole;
import com.vaadin.client.ui.VNotification;

public class CFloorGrid extends Composite implements ClickHandler, MouseDownHandler, MouseUpHandler, MouseMoveHandler, MouseOutHandler, ContextMenuHandler,
		ChangeHandler, KeyUpHandler {

	private static final String CLASSNAME = "c-floorgrid";
	private static final int GRID_SIZE = 50;

	private final Canvas canvas;
	private final List<CRoom> rooms = new LinkedList<CRoom>();
	private final Map<String, CRoom> roomMap = new HashMap<String, CRoom>();
	private final Set<String> names = new HashSet<String>();
	private CTable markedTable;

	private final TextBox typeAndEdit = new TextBox();

	private PopupPanel contextMenu;

	private InfoButton hoverElement = null;

	private int gridSize = 50;
	private int offsetX = 0;
	private int offsetY = 0;
	private final Point origo = new Point(0, 0);

	private int orgX, orgY;
	private Point orgOrigo = null;

	private String buttonColorPlus = "LAVENDER";
	private String buttonColorMinus = "LAVENDER";

	private boolean animating = false;
	private boolean mouseDown = false;
	private boolean mouseMoved = true;
	private int downX = 0;
	private int downY = 0;
	CRoom selected = null;
	Point targetPoint = null;
	int zoom = 0;

	boolean isEditable = false;
	private final FlowPanel content;

	public CFloorGrid() {
		content = new FlowPanel();
		content.setSize("100%", "100%");

		initWidget(content);

		// setElement(Document.get().createDivElement());
		setStyleName(CLASSNAME);

		addDomHandler(this, MouseDownEvent.getType());
		addDomHandler(this, MouseMoveEvent.getType());
		addDomHandler(this, MouseUpEvent.getType());
		addDomHandler(this, ClickEvent.getType());
		addDomHandler(this, ContextMenuEvent.getType());
		addDomHandler(this, ChangeEvent.getType());
		addDomHandler(this, MouseOutEvent.getType());
		addDomHandler(this, KeyUpEvent.getType());

		canvas = Canvas.createIfSupported();
		if (canvas != null) {
			// getElement().appendChild(canvas.getElement());
			content.add(canvas);
			clearCanvas();
			paint();
		} else {
			getElement().setInnerHTML("Canvas not supported");
		}

		// getElement().appendChild(typeAndEdit.getElement());
		content.add(typeAndEdit);

		final Style editStyle = typeAndEdit.getElement().getStyle();
		typeAndEdit.addChangeHandler(this);
		typeAndEdit.addKeyUpHandler(this);
		editStyle.setPosition(Position.RELATIVE);
		editStyle.setLeft(0.0, Style.Unit.PX);
		editStyle.setProperty("margin", "0");
		typeAndEdit.setWidth(Window.getClientWidth() + "px");

	}

	private void clearCanvas() {
		canvas.setCoordinateSpaceWidth(Window.getClientWidth());
		canvas.setCoordinateSpaceHeight(Window.getClientHeight() - typeAndEdit.getOffsetHeight() - 4);
	}

	public void addRoom(final RoomState roomState) {
		if (roomState.id != null && !roomMap.containsKey(roomState.id)) {
			final CRoom room = new CRoom(roomState.id, roomState.getPoints(), roomState.getPosition());
			room.setName(roomState.getName());
			for (final DoorState door : roomState.getDoor()) {
				room.addDoor(door);
			}
			for (final ItemState itemState : roomState.getItems()) {
				VisualItem item;
				switch (itemState.type) {
				case TABLE:
					item = new CTable(itemState.itemPoints, itemState.itemPosition);
					item.setName(itemState.itemName);
					if (itemState.itemName != null) {
						names.add(itemState.itemName);
					}
					break;
				default:
					item = new CItem(itemState.itemPoints, itemState.itemPosition);
				}
				room.addRoomItem(item);
			}
			rooms.add(room);
			roomMap.put(room.getId(), room);
			room.paint(canvas.getContext2d());
		}
	}

	public void addRoom(final CRoom room) {
		if (room.getId() != null && !roomMap.containsKey(room.getId())) {
			// final CRoom room = new CRoom(roomState.id, roomState.getPoints(),
			// roomState.getPosition());
			rooms.add(room);
			roomMap.put(room.getId(), room);
			room.paint(canvas.getContext2d());
		}
	}

	public List<CRoom> getRooms() {
		return new LinkedList<CRoom>(rooms);
	}

	public CRoom getRoom(final String id) {
		return roomMap.get(id);
	}

	public void paintRooms() {
		for (final CRoom room : rooms) {
			room.paint(canvas.getContext2d());
		}
	}

	SearchBar searchBar = new SearchBar(this);
	ButtonBar buttonBar = new ButtonBar(this);

	private void paint() {
		final Context2d context = canvas.getContext2d();

		GridUtils.paintGrid(context, new Point(offsetX, offsetY), gridSize, origo);
		GridUtils.paintZoomInButton(context, new Point(Window.getClientWidth() - 50, 25), 25, buttonColorPlus);
		GridUtils.paintZoomOutButton(context, new Point(Window.getClientWidth() - 50, 51), 25, buttonColorMinus);
		paintRooms();

		searchBar.paint(context);
		buttonBar.paint(context);

		if (hoverElement != null) {
			hoverElement.paint(context);
			// hoverElement.getRoom().paintLabel(context);
		}
	}

	private void repaint() {
		clearCanvas();
		paint();
	}

	@Override
	public void onClick(final ClickEvent event) {
		if (animating) {
			return;
		}
		if (typeAndEdit.getElement().equals(event.getNativeEvent().getEventTarget())) {
			return;
		}
		if (event.getNativeButton() == NativeEvent.BUTTON_RIGHT) {
		} else {
			if (mouseMoved) {
				mouseMoved = false;
				return;
			}
			final int clientX = event.getClientX();
			final int clientY = event.getClientY();
			if (clientX > Window.getClientWidth() - 50 && clientX < Window.getClientWidth() - 25) {
				if (clientY > 25 && clientY < 50) {
					// sx = sy = 2
					// x2 = sx*x1
					// y2 = sy*y1
					zoom++;
					if (zoom == 0) {
						reset();
					} else {
						scale(2);
					}
					repaint();
					return;
				} else if (clientY > 51 && clientY < 76) {
					// sx = sy = 0.5
					// x2 = sx*x1
					// y2 = sy*y1
					zoom--;
					if (zoom == 0) {
						reset();
					} else {
						scale(0.5);
					}
					repaint();
					return;
				}
			}

			if (hoverElement != null && hoverElement.pointInObject(clientX, clientY)) {
				// fireEvent(new
				// MenuEvent(MenuEvent.MenuEventType.OPEN_ROOM_INFO,
				// hoverElement.getRoom().getId()));
				final CInfoEditor info = new CInfoEditor(this, hoverElement.getRoom());
				info.setPopupPosition((Window.getClientWidth() / 2) - 350, (Window.getClientHeight() / 2) - 200);
				info.show();
			}
			if (isEditable) {
				boolean selected = false;
				for (final CRoom room : rooms) {
					if (room.pointInObject(clientX, clientY) && !selected) {
						room.setSelection(true);
						selected = true;
					} else {
						room.setSelection(false);
					}
				}
			} else {
				for (final CRoom room : rooms) {
					room.clicked(downX, downY);
				}
			}
			buttonBar.click(clientX, clientY);
			repaint();
		}
	}

	private void scale(final double scale) {
		if (orgOrigo == null) {
			orgOrigo = new Point(origo.getX(), origo.getY());
			orgX = offsetX;
			orgY = offsetY;
		}
		for (final CRoom room : rooms) {
			room.scale(scale);
		}
		offsetX = (int) Math.ceil(offsetX * scale);
		offsetY = (int) Math.ceil(offsetY * scale);
		gridSize = (int) Math.ceil(gridSize * scale);
		origo.setX((int) Math.ceil(origo.getX() * scale));
		origo.setY((int) Math.ceil(origo.getY() * scale));
	}

	private void reset() {
		origo.setX(orgOrigo.getX());
		origo.setY(orgOrigo.getY());
		gridSize = GRID_SIZE;
		offsetX = orgX;
		offsetY = orgY;
		orgOrigo = null;

		for (final CRoom room : rooms) {
			room.reset();
		}
	}

	@Override
	public void onMouseDown(final MouseDownEvent event) {
		if (animating) {
			return;
		}
		downX = event.getClientX();
		downY = event.getClientY();
		mouseDown = true;
		if (contextMenu != null) {
			contextMenu.hide();
			contextMenu = null;
		}
		if (isEditable) {
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
	}

	@Override
	public void onMouseUp(final MouseUpEvent event) {
		mouseDown = false;
		selected = null;
	}

	@Override
	public void onMouseMove(final MouseMoveEvent event) {
		if (animating) {
			return;
		}
		final int clientX = event.getClientX();
		final int clientY = event.getClientY();
		if (mouseDown) {
			mouseMoved = true;

			if (selected == null) {
				pan(event);
			} else {
				moveRoom(selected, event);
			}

			downX = clientX;
			downY = clientY;

			repaint();
		} else if (hoverElement == null) {
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

		// Change coloring of + and - buttons if hovered over.
		if (clientX > Window.getClientWidth() - 50 && clientX < Window.getClientWidth() - 25) {
			if (clientY > 25 && clientY < 50) {
				buttonColorPlus = "SILVER";
				buttonColorMinus = "LAVENDER";
			} else if (clientY > 51 && clientY < 76) {
				buttonColorMinus = "SILVER";
				buttonColorPlus = "LAVENDER";
			}
		} else {
			buttonColorPlus = "LAVENDER";
			buttonColorMinus = "LAVENDER";
		}
		if (searchBar.mouseOver(clientX, clientY)) {
			if (!searchBar.isVisible()) {
				searchBar.setAnimate(true);
				searchBar.setVisible(true);
			}
		} else if (searchBar.isVisible()) {
			searchBar.setAnimate(true);
			searchBar.setVisible(false);
		}
		if (buttonBar.mouseOver(clientX, clientY)) {
			if (!buttonBar.isVisible()) {
				buttonBar.setAnimate(true);
				buttonBar.setVisible(true);
			}
		} else if (buttonBar.isVisible()) {
			buttonBar.setAnimate(true);
			buttonBar.setVisible(false);
		}
		repaint();
	}

	private void pan(final MouseMoveEvent event) {
		pan(event.getClientX() - downX, event.getClientY() - downY);
	}

	private void pan(final int amountx, final int amounty) {
		offsetX += amountx;
		offsetY += amounty;

		offsetX = offsetX % 50;
		offsetY = offsetY % 50;

		origo.move(amountx, amounty);
		if (orgOrigo != null) {
			orgOrigo.move(amountx, amounty);
		}
		for (final CRoom room : rooms) {
			room.movePosition(amountx, amounty);
		}
	}

	private void moveRoom(final CRoom room, final MouseMoveEvent event) {
		if (targetPoint == null) {
			if (event.isAltKeyDown()) {
			}
			room.movePosition(event.getClientX() - downX, event.getClientY() - downY);
		} else {
			room.pointMoved();
			targetPoint.move(event.getClientX() - downX, event.getClientY() - downY);
		}
	}

	boolean noChangeEvent = false;

	@Override
	public void onKeyUp(final KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			noChangeEvent = true;
			handleTextFieldValue(null);
		}
	}

	@Override
	public void onChange(final ChangeEvent event) {
		if (noChangeEvent) {
			noChangeEvent = false;
		} else {
			handleTextFieldValue(null);
		}
	}

	protected void setAnimating(final boolean animating) {
		this.animating = animating;
	}

	protected void handleTextFieldValue(String value) {
		if (value == null) {
			value = typeAndEdit.getValue();
		}

		if (value != null && !value.isEmpty()) {
			final CommandObject cmd = new CommandObject(value);
			for (final CRoom room : rooms) {
				if (room.isSelected()) {
					switch (cmd.getCommand()) {
					case MOVE_TO:
						room.setPosition(GeometryUtil.combine(origo, cmd.getPosition()));
						typeAndEdit.setValue("");
						break;
					case MOVE_BY:
						room.movePosition(cmd.getX(), cmd.getY());
						typeAndEdit.setValue("");
						break;
					case SAVE:
						fireEvent(new MenuEvent(MenuEvent.MenuEventType.UPDATE_ROOMS));
						typeAndEdit.setValue("");
						break;
					case INVALID_STRING:
						Window.alert("Command String was invalid");
						break;
					case PARSE_FAILED:
						Window.alert("Parsing coordinates failed");
						break;
					}
					repaint();
					break;
				}
			}
			switch (cmd.getCommand()) {
			case PAN:
				pan(cmd.getX(), cmd.getY());
				typeAndEdit.setValue("");
				repaint();
				break;
			case FIND:
				if (names.contains(cmd.getValue())) {
					typeAndEdit.setValue("");

					markTableOfSelectedPerson(cmd.getValue());
				} else if (namesContain(cmd)) {
					typeAndEdit.setValue("");
					final LinkedList<String> possible = possibilities(cmd);
					if (possible.size() == 1) {
						markTableOfSelectedPerson(possible.getFirst());
					} else if (possible.size() > 1) {
						new NameSelectPopup(possible, this);
					}
				} else {
					final VNotification notification = new VNotification();
					final Style style = notification.getElement().getStyle();
					style.setBackgroundColor("#c8ccd0");
					style.setPadding(15.0, Unit.PX);
					style.setProperty("border-radius", "4px");
					style.setProperty("-moz-border-radius", "4px");
					style.setProperty("-webkit-border-radius", "4px");

					notification.show("No user found for [" + cmd.getValue() + "]", VNotification.CENTERED, null);
				}
				break;
			}
		}
	}

	private boolean namesContain(final CommandObject cmd) {
		for (final String name : names) {
			if (name.toLowerCase().contains(cmd.getValue().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private LinkedList<String> possibilities(final CommandObject cmd) {
		final LinkedList<String> possible = new LinkedList<String>();

		for (final String name : names) {
			if (name.toLowerCase().contains(cmd.getValue().toLowerCase())) {
				possible.add(name);
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
		final double xPointInCanvas = (canvas.getCoordinateSpaceWidth() / 2) - (table.maxX() - table.minX()) / 2;
		final double yPointInCanvas = (canvas.getCoordinateSpaceHeight() / 2) - (table.maxY() - table.minY()) / 2;

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
				pan((int) Math.floor(moveX), (int) Math.floor(moveY));
				repaint();
			}

			@Override
			protected void onComplete() {
				super.onComplete();
				animating = false;
			}
		};
		animating = true;
		VConsole.log(" -- X: " + panX + " Y: " + panY);
		animate.run(Math.abs(panX) > Math.abs(panY) ? (int) (Math.abs(panX)) : (int) (Math.abs(panY)));
	}

	@Override
	public void onContextMenu(final ContextMenuEvent event) {
		if (!isEditable) {
			return;
		}
		event.preventDefault();
		mouseDown = false;
		selected = null;

		final int x = event.getNativeEvent().getClientX();
		final int y = event.getNativeEvent().getClientY();

		final MenuBar rootMenu = new MenuBar(true);
		final MenuBar roomMenu = new MenuBar(true);

		final MenuItem triangle = new MenuItem("3 corners", new AddCommand(3, x, y));
		final MenuItem square = new MenuItem("4 corners", new AddCommand(4, x, y));
		final MenuItem fivePoints = new MenuItem("5 corners", new AddCommand(5, x, y));
		final MenuItem LShape = new MenuItem("L shaped 6 corners", new AddCommand(6, x, y));

		roomMenu.addItem(triangle);
		roomMenu.addItem(square);
		roomMenu.addItem(fivePoints);
		roomMenu.addItem(LShape);

		final MenuItem newRoom = new MenuItem("Add new room", roomMenu);
		rootMenu.addItem(newRoom);

		rootMenu.setVisible(true);
		contextMenu = new PopupPanel();
		contextMenu.add(rootMenu);

		Style style = rootMenu.getElement().getStyle();
		style.setProperty("backgroundColor", "gray");
		style.setProperty("borderColor", "gray");
		style.setProperty("borderWidth", "1px 3px 3px 1px");
		style.setProperty("borderStyle", "solid");

		style = roomMenu.getElement().getStyle();
		style.setProperty("backgroundColor", "gray");
		style.setProperty("borderColor", "gray");
		style.setProperty("borderWidth", "1px 3px 3px 1px");
		style.setProperty("borderStyle", "solid");

		chekcForRemoveAndAddItem(rootMenu);

		rootMenu.addItem(new MenuItem("Push states to server", new Command() {
			@Override
			public void execute() {
				fireEvent(new MenuEvent(MenuEvent.MenuEventType.UPDATE_ROOMS));
				contextMenu.hide();
				contextMenu = null;
			}
		}));

		contextMenu.setPopupPosition(x, y);
		contextMenu.show();
	}

	private void chekcForRemoveAndAddItem(final MenuBar rootMenu) {
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

	private class AddCommand implements Command {

		private final int corners, x, y;

		public AddCommand(final int corners, final int x, final int y) {
			this.corners = corners;
			this.x = x;
			this.y = y;
		}

		@Override
		public void execute() {
			fireEvent(new MenuEvent(MenuEvent.MenuEventType.ADD_ROOM, corners, x, y));
			contextMenu.hide();
			contextMenu = null;
		}
	}

	private class RemoveCommand implements Command {
		private final CRoom selectedRoom;

		public RemoveCommand(final CRoom selectedRoom) {
			this.selectedRoom = selectedRoom;
		}

		@Override
		public void execute() {
			rooms.remove(selectedRoom);
			fireEvent(new MenuEvent(MenuEvent.MenuEventType.REMOVE_ROOM, selectedRoom.getId()));
			contextMenu.hide();
			contextMenu = null;
			repaint();
		}
	}

	@Override
	public void onMouseOut(final MouseOutEvent event) {
		mouseDown = false;
		selected = null;
	}

	public void setEditable(final boolean editable) {
		isEditable = editable;
	}

	boolean showNames = false;

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

}
