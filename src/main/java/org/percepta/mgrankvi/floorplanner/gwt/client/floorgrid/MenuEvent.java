package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import org.percepta.mgrankvi.floorplanner.gwt.client.room.CRoom;

import com.google.gwt.event.shared.GwtEvent;

public class MenuEvent extends GwtEvent<MenuEventHandler> {

    public static enum MenuEventType {
        ADD_ROOM, REMOVE_ROOM, OPEN_ROOM_INFO, UPDATE_ROOMS, ADD_TABLE
    }

    private static Type<MenuEventHandler> TYPE;

    private final MenuEventType eventType;
    private int value, x, y;
    private CRoom room;

    public MenuEvent(final MenuEventType eventType) {
        this.eventType = eventType;
    }

    public MenuEvent(final MenuEventType eventType, final int value) {
        this.eventType = eventType;
        this.value = value;
    }

    public MenuEvent(final MenuEventType eventType, final CRoom room) {
        this.eventType = eventType;
        this.room = room;
    }

    public MenuEvent(final MenuEventType eventType, final int value, final int x, final int y) {
        this.eventType = eventType;
        this.value = value;
        this.x = x;
        this.y = y;
    }

    @Override
    public Type<MenuEventHandler> getAssociatedType() {
        return getType();
    }

    public static Type<MenuEventHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<MenuEventHandler>();
        }
        return TYPE;
    }

    @Override
    protected void dispatch(final MenuEventHandler handler) {
        handler.onMenuEvent(this);
    }

    public MenuEventType getEventType() {
        return eventType;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CRoom getRoom() {
        return room;
    }

}
