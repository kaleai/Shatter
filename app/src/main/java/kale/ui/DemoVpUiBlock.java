package kale.ui;

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

    /**
     * 如果你是把这个放入viewpager中，你就必须要复写这个方法，重置下根布局
     */
    @Override
    public View resetRootView(View oldRootView, LayoutInflater inflater) {
        return inflater.inflate(R.layout.demo_vp_uiblock, null);
    }

    @Override
    public void onBindViews(View rootView) {
            
    }

    @Override
    public void onSetViews() {
        ((TextView) getView(R.id.header_tv)).setTextSize(30);
    }

    @Override
    public void onVisibleToUser(boolean isVisible) {
        super.onVisibleToUser(isVisible);
    }
}
