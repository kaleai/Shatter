package kale.ui;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import kale.ui.uiblock.UIBlock;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoInnerUIBlock extends UIBlock {

    @Override
    public View initRootView(Activity activity) {
        return activity.findViewById(R.id.inner_tv);
    }

    @Override
    protected void onBindViews() {
        
    }

    @Override
    protected void onSetViews() {
        ((TextView) getView(R.id.inner_tv)).setText("Inner UI Block :-)");
    }
}
