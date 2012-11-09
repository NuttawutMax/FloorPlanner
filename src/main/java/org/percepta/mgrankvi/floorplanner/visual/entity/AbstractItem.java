package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemState;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemType;

public class AbstractItem {

	protected ItemState state = new ItemState();
	protected ItemType type;
	protected List<Point> points = new LinkedList<Point>();

}
