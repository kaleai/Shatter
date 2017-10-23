package kale.ui.uimodule.adapter;

import android.app.Activity;

import kale.ui.uimodule.UiModule;

/**
 * @author Kale
 * @date 2016/7/4
 */
public abstract class ViewPagerUiModule extends UiModule {

    @Override
    protected void attachActivity(Activity activity) {
        super.attachActivity(activity);
        onStart();
        onResume();
    }
}
