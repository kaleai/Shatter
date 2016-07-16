package kale.ui.uiblock;

import android.app.Activity;

/**
 * @author Kale
 * @date 2016/7/4
 */
public abstract class ViewPagerUiBlock extends UiBlock{

    @Override
    protected void attachActivity(Activity activity) {
        super.attachActivity(activity);
        onStart();
        onResume();
    }
}
