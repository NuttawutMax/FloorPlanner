package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import java.util.List;

import org.percepta.mgrankvi.floorplanner.gwt.client.geometry.Point;

import com.vaadin.shared.communication.ServerRpc;

public interface FloorServerRpc extends ServerRpc {

    public void addNewRoom(int value, int x, int y);

    public void removeRoom(String id);

    public void openRoomInformationWindow(String id);

    public void updateVisualItem(String id, Point position, List<Point> points);
}
