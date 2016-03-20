package kale.ui.block;

import android.view.View;
import android.widget.TextView;

import kale.ui.R;
import kale.ui.uiblock.UiBlock;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoInnerUiBlock extends UiBlock {

    @Override
    protected int getLayoutResId() {
        return R.layout.demo_activity;
    }

    @Override
    public void bindViews(View rootView) {
        
    }

    @Override
    public void setViews() {
        ((TextView) getView(R.id.inner_tv)).setText(R.string.test_text);
    }
}
