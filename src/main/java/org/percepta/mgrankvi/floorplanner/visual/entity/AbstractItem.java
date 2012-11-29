package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemType;

import com.vaadin.ui.AbstractComponent;

public class AbstractItem extends AbstractComponent {

	private static final long serialVersionUID = -1641629960814112467L;

	protected ItemType type;
	protected List<Point> points = new LinkedList<Point>();

}
