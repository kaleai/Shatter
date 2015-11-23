package kale.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import kale.ui.uiblock.UIBlock;

/**
 * @author Jack Tony
 * @date 2015/11/21
 */
public class DemoVpUIBlock extends UIBlock<DemoActivity> {

    @Override
    public View initRootView(Activity activity) {
        return LayoutInflater.from(activity).inflate(R.layout.demo_vp_uiblock, null);
    }

    @Override
    protected void onBindViews() {
            
    }

    @Override
    protected void onSetViews() {
        ((TextView) getView(R.id.header_tv)).setTextSize(30);
    }
}
