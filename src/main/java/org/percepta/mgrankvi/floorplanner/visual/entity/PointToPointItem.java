package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.UUID;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;
import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Rectangle;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemType;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.pointtopoint.PointToPointItemState;

public class PointToPointItem extends AbstractItem {

	private static final long serialVersionUID = -8375292297427422389L;

	public PointToPointItem(final Point position, final Point... points) {
		for (final Point point : points) {
			getState().addPoint(point);
		}
		getState().type = ItemType.POINT_TO_POINT_ITEM;
		getState().id = UUID.randomUUID().toString();
		setPosition(position);
	}

	public void setSize(final int width, final int height) {
		getState().addPoints(new Rectangle(width, height).getCorners());
	}

	public void setPosition(final Point position) {
		getState().setPosition(position);
	}

	@Override
	public PointToPointItemState getState() {
		return (PointToPointItemState) super.getState();
	}

	public void setName(final String name) {
		getState().itemName = name;
	}

	public String getName() {
		return getState().itemName;
	}
}
