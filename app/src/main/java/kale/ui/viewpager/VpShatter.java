package kale.ui.viewpager;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import kale.ui.R;
import kale.ui.shatter.Shatter;

/**
 * @author Jack Tony
 * @date 2015/11/21
 */
public class VpShatter extends Shatter {

    private static final String TAG = "VpShatter";
    
    @Override
    protected int getLayoutResId() {
        return R.layout.pager_shatter;
    }

    @Override
    public void bindViews(View rootView) {
    }

    @Override
    public void setViews() {
        ((TextView) findViewById(R.id.header_tv)).setTextSize(30);
    }

    @Override
    public void handleData(Object model, int position) {
        Log.d(TAG, "handleData: pos = " + position);
        ((TextView) findViewById(R.id.header_tv)).setText((CharSequence) model);
    }

    @Override
    public void onVisibleToUser(boolean isVisible) {
        super.onVisibleToUser(isVisible);
        // 被用户可见时的回调
    }
}
