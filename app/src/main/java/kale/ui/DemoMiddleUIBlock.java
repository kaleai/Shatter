package kale.ui;

import kale.ui.base.BaseActivity;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoMiddleUIBlock extends UIBlock{

    @Override
    public int getRootViewId() {
        return R.id.middle_ub;
    }

    @Override
    protected void bindViews() {
        getActivity(BaseActivity.class).getUIBlockManager().add(new DemoInnerUIBlock());
    }

    @Override
    protected void setViews() {
        getRootView().setBackgroundColor(0xff65a8b7);
    }
}
