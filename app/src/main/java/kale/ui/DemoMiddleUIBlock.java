package kale.ui;

import android.app.Activity;
import android.view.View;

import kale.ui.uiblock.UIBlock;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoMiddleUIBlock extends UIBlock {

    @Override
    public View initRootView(Activity activity) {
        return activity.findViewById(R.id.middle_ub);
    }

    @Override
    protected void onBindViews() {
        getActivity().getUiBlockManager().add(new DemoInnerUIBlock());
    }

    @Override
    protected void onSetViews() {
        getRootView().setBackgroundColor(0xffff5722);
    }
}
