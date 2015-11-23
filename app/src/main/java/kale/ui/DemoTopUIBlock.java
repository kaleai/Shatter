package kale.ui;

import android.app.Activity;
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

    @Override
    public View initRootView(Activity activity) {
        return activity.findViewById(R.id.top_ub);
    }

    @Bind(R.id.top_tv)
    TextView mTopTv;

    @Override
    protected void onBindViews() {
        ButterKnife.bind(this, getRootView());
        //mTopTv = getView(R.id.top_tv);
    }

    @Override
    protected void onSetViews() {
        String content = mTopTv.getText().toString();
        mTopTv.setText(content + " :)");
    }
    
}
