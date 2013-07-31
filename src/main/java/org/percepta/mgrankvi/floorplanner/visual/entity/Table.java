package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.UUID;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Rectangle;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemType;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.table.TableState;

public class Table extends AbstractItem {

    private static final long serialVersionUID = -8375292297427422389L;

    public Table() {
        getState().type = ItemType.TABLE;
        getState().id = UUID.randomUUID().toString();
    }

    public Table(final String name, final Point position) {
        getState().type = ItemType.TABLE;
        getState().id = UUID.randomUUID().toString();
        setName(name);
        setPosition(position);
    }

    public void setName(final String name) {
        getState().setName(name);
    }

    public String getName() {
        return getState().getName();
    }

    public void setSize(final int width, final int height) {
        getState().addPoints(new Rectangle(width, height).getCorners());
    }

    public void setPosition(final Point position) {
        getState().setPosition(position);
    }

    public void linkToNode(final int nodeId) {
        getState().nodeId = nodeId;
    }

    @Override
    public TableState getState() {
        return (TableState) super.getState();
    }
}
