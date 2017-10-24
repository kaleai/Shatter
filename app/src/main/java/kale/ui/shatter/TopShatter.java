package kale.ui.shatter;

import android.view.View;
import android.widget.TextView;

import kale.ui.R;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class TopShatter extends Shatter {

    private TextView mTopTv;

    @Override
    protected int getLayoutResId() {
        return NO_LAYOUT;
    }

    @Override
    public void bindViews(View rootView) {
        mTopTv = findViewById(R.id.top_tv);
    }

    @Override
    public void setViews() {
        mTopTv.append("  :)");
    }

}
