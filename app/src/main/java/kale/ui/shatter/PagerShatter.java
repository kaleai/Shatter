package kale.ui.shatter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import kale.ui.R;

/**
 * @author Jack Tony
 * @date 2015/11/21
 */
public class PagerShatter extends Shatter {

    public PagerShatter() {
        Log.d("PagerShatter", "new PagerShatter " + this.toString());
    }

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
    public void onVisibleToUser(boolean isVisible) {
        super.onVisibleToUser(isVisible);
        // 被用户可见时的回调，可做懒加载
    }

    public void handleData(String data) {
        ((TextView) findViewById(R.id.header_tv)).setText(data);
    }

}
