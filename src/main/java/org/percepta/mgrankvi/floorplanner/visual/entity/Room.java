package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.room.RoomState;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;

public class Room extends AbstractComponentContainer implements HasComponents {

    private static final long serialVersionUID = 1050249067936900003L;

    List<Component> children = new LinkedList<Component>();

    public Room(final Point... points) {
        for (final Point point : points) {
            getState().addPoint(point);
        }
        getState().id = UUID.randomUUID().toString();
    }

    @Override
    public RoomState getState() {
        return (RoomState) super.getState();
    }

    @Override
    public String getId() {
        return getState().id;
    }

    public void setPosition(final Point position) {
        getState().position = position;
    }

    public void setPoints(final List<Point> points) {
        getState().points = points;
    }

    public void setName(final String name) {
        getState().name = name;
    }

    public String getName() {
        return getState().name;
    }

    @Override
    public void addComponent(final Component c) {
        children.add(c);
        super.addComponent(c);
        markAsDirty();
    }

    @Override
    public void removeComponent(final Component c) {
        children.remove(c);
        super.removeComponent(c);
        markAsDirty();
    }

    @Override
    public void replaceComponent(final Component oldComponent, final Component newComponent) {
        final int index = children.indexOf(oldComponent);
        if (index != -1) {
            children.remove(index);
            children.add(index, newComponent);
            fireComponentDetachEvent(oldComponent);
            fireComponentAttachEvent(newComponent);
            markAsDirty();
        }
    }

    @Override
    public int getComponentCount() {
        return children.size();
    }

    @Override
    public Iterator<Component> iterator() {
        return children.iterator();
    }
}
