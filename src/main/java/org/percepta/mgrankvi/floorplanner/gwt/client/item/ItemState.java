package org.percepta.mgrankvi.floorplanner.gwt.client.item;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.vaadin.shared.ComponentState;

public class ItemState extends ComponentState {

	public ItemType type;
	public List<Point> itemPoints = new LinkedList<Point>();
	public Point itemPosition;
	public String itemName;
}
