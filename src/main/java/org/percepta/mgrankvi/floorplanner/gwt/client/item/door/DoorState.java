package org.percepta.mgrankvi.floorplanner.gwt.client.item.door;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemType;

import com.vaadin.shared.AbstractComponentState;

public class DoorState extends AbstractComponentState {

    private static final long serialVersionUID = 6186956716133798868L;

    public enum Direction {
        DOWN_LEFT, UP_LEFT, DOWN_RIGHT, UP_RIGHT
    }

    public Direction openingDirection;
    public Point position;
    public int size = 20;

    public ItemType type;

}
