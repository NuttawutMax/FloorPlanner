package org.percepta.mgrankvi.floorplanner.visual.entity;

import java.util.UUID;

import org.percepta.mgrankvi.floorplanner.gwt.client.item.ItemType;

public class Table extends AbstractItem {

	public Table() {
		type = ItemType.TABLE;
		state.id = UUID.randomUUID().toString();
	}
}
