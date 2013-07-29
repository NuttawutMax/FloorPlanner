package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.DelinkedNode;

import com.vaadin.shared.AbstractComponentState;

public class PathWaypointState extends AbstractComponentState {

    private static final long serialVersionUID = -863840405464639721L;

    public List<DelinkedNode> nodes = new LinkedList<DelinkedNode>();
}
