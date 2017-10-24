package kale.ui.shatter.adapter;

import android.app.Activity;

import kale.ui.shatter.Shatter;

/**
 * @author Kale
 * @date 2016/7/4
 */
public abstract class ViewPagerShatter extends Shatter {

    @Override
    protected void attachActivity(Activity activity) {
        super.attachActivity(activity);
        onStart();
        onResume();
    }
}
