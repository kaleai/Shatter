package kale.ui;

import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class DemoTopUIBlock extends UIBlock{

    @Override
    public int getRootViewId() {
        return R.id.top_ub;
    }

    @Bind(R.id.top_tv)
    TextView mTopTv;

    @Override
    protected void bindViews() {
        ButterKnife.bind(this,getRootView());
        //mTopTv = getView(R.id.top_tv);
    }

    @Override
    protected void setViews() {
        String content = mTopTv.getText().toString();
        mTopTv.setText(content + " :)");
    }
    
}
