package org.percepta.mgrankvi.floorplanner.gwt.client.item.table;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.visual.entity.Table.class)
public class TableConnector extends AbstractComponentConnector {

    private static final long serialVersionUID = -4019614673560751414L;

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(CTable.class);
    }

    @Override
    public CTable getWidget() {
        return (CTable) super.getWidget();
    };

    @Override
    public TableState getState() {
        return (TableState) super.getState();
    }

    @Override
    public void onStateChanged(final StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        getWidget().setPosition(getState().itemPosition);
        getWidget().setPoints(getState().itemPoints);
        getWidget().setName(getState().itemName);
        getWidget().setNodeId(getState().nodeId);
    }
}
