package org.percepta.mgrankvi.floorplanner.gwt.client.item.pointtopoint;

import org.percepta.mgrankvi.floorplanner.gwt.client.item.CItem;
import org.percepta.mgrankvi.floorplanner.gwt.client.item.PaintableItemState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.percepta.mgrankvi.floorplanner.visual.entity.PointToPointItem.class)
public class PointToPointItemConnector extends AbstractComponentConnector {

    private static final long serialVersionUID = -4019614673560751414L;

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(CItem.class);
    }

    @Override
    public CItem getWidget() {
        return (CItem) super.getWidget();
    };

    @Override
    public PaintableItemState getState() {
        return (PaintableItemState) super.getState();
    }

    @Override
    public void onStateChanged(final StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        getWidget().setPosition(getState().itemPosition);
        getWidget().setPoints(getState().itemPoints);
        getWidget().setName(getState().itemName);
        if (getState().fillColor != null) {
            getWidget().setFillColor(getState().fillColor);
        }
    }
}
