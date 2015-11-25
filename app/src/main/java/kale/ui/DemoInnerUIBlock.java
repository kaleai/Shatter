package kale.ui;

import android.widget.TextView;

import kale.ui.uiblock.UIBlock;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoInnerUIBlock extends UIBlock {

    @Override
    public int getRootViewId() {
        return R.id.inner_tv;
    }

    @Override
    protected void onBindViews() {
        
    }

    @Override
    protected void onSetViews() {
        ((TextView) getView(R.id.inner_tv)).setText("Inner UI Block :-)");
    }
}
