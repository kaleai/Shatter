package kale.ui.block;

import android.view.View;
import android.widget.TextView;

import kale.ui.R;
import kale.ui.uiblock.UiBlock;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class DemoTopUiBlock extends UiBlock {

    TextView mTopTv;

    @Override
    protected int getLayoutResId() {
        return R.layout.demo_activity;
    }

    @Override
    public void bindViews(View rootView) {
        mTopTv = getView(R.id.top_tv);
    }

    @Override
    public void setViews() {
        String content = mTopTv.getText().toString();
        mTopTv.setText(content + " :)");
    }
    
}
