package kale.ui.block;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import kale.ui.R;
import kale.ui.uiblock.UiBlock;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class DemoTopUiBlock extends UiBlock {

    @Bind(R.id.top_tv)
    TextView mTopTv;

    @Override
    protected int getLayoutResId() {
        return R.layout.demo_activity;
    }

    @Override
    public void bindViews(View rootView) {
        ButterKnife.bind(this, getRootView());
    }

    @Override
    public void setViews() {
        String content = mTopTv.getText().toString();
        mTopTv.setText(content + " :)");
    }
    
}
