package kale.ui;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import kale.ui.uiblock.UIBlock;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class DemoTopUIBlock extends UIBlock {

    @Bind(R.id.top_tv)
    TextView mTopTv;

    @Override
    public int getRootViewId() {
        return R.id.top_ub;
    }

    @Override
    public void onBindViews(View rootView) {
        ButterKnife.bind(this, getRootView());
        //mTopTv = getView(R.id.top_tv);
    }

    @Override
    public void onSetViews() {
        String content = mTopTv.getText().toString();
        mTopTv.setText(content + " :)");
    }
    
}
