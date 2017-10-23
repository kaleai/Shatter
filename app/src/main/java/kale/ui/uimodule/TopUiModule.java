package kale.ui.uimodule;

import android.view.View;
import android.widget.TextView;

import kale.ui.R;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class TopUiModule extends UiModule {

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
