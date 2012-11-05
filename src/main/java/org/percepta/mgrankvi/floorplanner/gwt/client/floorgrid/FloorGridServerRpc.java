package org.percepta.mgrankvi.floorplanner.gwt.client.floorgrid;

import com.vaadin.shared.communication.ServerRpc;

public interface FloorGridServerRpc extends ServerRpc {

	public void addNewRoom(int value, int x, int y);

	public void removeRoom(String id);

	public void openRoomInformationWindow(String id);
}
