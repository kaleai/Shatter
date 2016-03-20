package kale.ui.block;

import android.view.View;

import kale.ui.R;
import kale.ui.uiblock.UiBlock;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoMiddleUiBlock extends UiBlock {

    @Override
    protected int getLayoutResId() {
        return R.layout.demo_activity;
    }

    @Override
    public void bindViews(View rootView) {
        getManager().add(R.id.inner_tv, new DemoInnerUiBlock());
    }

    @Override
    public void setViews() {
        getRootView().setBackgroundColor(0xffff5722);
    }
}
