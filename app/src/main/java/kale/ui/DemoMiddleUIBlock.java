package kale.ui;

import kale.ui.uiblock.UIBlock;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoMiddleUIBlock extends UIBlock {


    @Override
    public int getRootViewId() {
        return R.id.middle_ub;
    }

    @Override
    protected void onBindViews() {
        getActivity().getUIBlockManager().add(new DemoInnerUIBlock());
    }

    @Override
    protected void onSetViews() {
        getRootView().setBackgroundColor(0xffff5722);
    }
}
