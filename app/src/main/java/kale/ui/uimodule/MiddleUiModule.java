package kale.ui.uimodule;

import android.view.View;

import kale.ui.R;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class MiddleUiModule extends UiModule {

    @Override
    protected int getLayoutResId() {
        return R.layout.demo_activity;
    }

    @Override
    public void bindViews(View rootView) {
        getUiBlockManager().add(R.id.inner_tv, new InnerUiModule());
    }

    @Override
    public void setViews() {
        getRootView().setBackgroundColor(0xffff5722);
    }
}
