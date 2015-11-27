package kale.ui;

import android.view.View;

import kale.ui.uiblock.UIBlock;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoMiddleUIBlock extends UIBlock<DemoActivity> {

    @Override
    public int getRootViewId() {
        return R.id.middle_ub;
    }

    @Override
    public void onBindViews(View rootView) {
        getActivity().getUIBlockManager().add(new DemoInnerUIBlock());
    }

    @Override
    public void onSetViews() {
        getRootView().setBackgroundColor(0xffff5722);
    }
}
