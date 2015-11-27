package kale.ui;

import android.view.View;
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
    public void onBindViews(View rootView) {
        
    }

    @Override
    public void onSetViews() {
        ((TextView) getView(R.id.inner_tv)).setText("Inner UI Block :-)");
    }
}
