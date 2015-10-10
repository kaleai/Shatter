package kale.ui;

import android.widget.TextView;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class DemoTopUIBlock extends UIBlock{

    @Override
    public int getRootViewId() {
        return R.id.top_ub;
    }

    TextView mTopTv;

    @Override
    protected void bindViews() {
        mTopTv = getView(R.id.top_tv);
    }

    @Override
    protected void setViews() {
        String content = mTopTv.getText().toString();
        mTopTv.setText(content + " :)");
    }
    
}
