package org.percepta.mgrankvi.floorplanner;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid.FloorGridState;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;

public class FloorGrid extends AbstractComponentContainer implements HasComponents {

	private static final long serialVersionUID = 6976301197921043544L;

	List<Component> children = new LinkedList<Component>();

	public FloorGrid() {
		setWidth("100%");
		setHeight("100%");
	}

	public void setEditable(final boolean editable) {
		getState().editable = editable;
	}

	public boolean getEditable() {
		return getState().editable;
	}

	@Override
	protected FloorGridState getState() {
		return (FloorGridState) super.getState();
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
