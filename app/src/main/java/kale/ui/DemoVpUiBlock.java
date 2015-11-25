package kale.ui;

import android.util.Log;
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
    public int getRootViewId() {
        return R.id.top_vp;
    }

    @Override
    public View resetRootView(View oldRootView, LayoutInflater inflater) {
        return inflater.inflate(R.layout.demo_vp_uiblock, null);
    }

    @Override
    protected void onBindViews() {
            
    }

    @Override
    protected void onSetViews() {
        ((TextView) getView(R.id.header_tv)).setTextSize(30);
    }

    @Override
    public void onVisibleToUser(boolean isVisible) {
        super.onVisibleToUser(isVisible);
        Log.d("ddd", "onVisibleToUser: " + isVisible);
    }
}
