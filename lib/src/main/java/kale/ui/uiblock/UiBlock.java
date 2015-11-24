package kale.ui.uiblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import kale.ui.uiblock.iface.ActivityLifecycle;

/**
 * @author Jack Tony
 * @date 2015/6/15
 * 1.需要在界面销毁时把回调停止
 */
public abstract class UIBlock<T extends ContainUIBlockActivity> implements ActivityLifecycle{

    private View mRootView;

    private T mActivity;

    protected void attachActivity(T activity) {
        onAttach(activity);
        mRootView = initRootView((Activity) activity);
        onBindViews();
        beforeSetViews();
        onSetViews();
    }

    protected void onAttach(T activity) {
        mActivity = activity;
    }

    /**
     * @return 的根view
     */
    public abstract View initRootView(Activity activity);

    /**
/     * 找到所有的views
     */
    protected abstract void onBindViews();

    /**
     * 在这里初始化设置view的各种资源，比如适配器或各种变量
     */
    protected void beforeSetViews() {}

    /**
     * 设置所有的view
     */
    protected abstract void onSetViews();

    public View getRootView() {
        return mRootView;
    }

    protected T getActivity() {
        return mActivity;
    }

    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {}

    public void onRestoreInstanceState(Bundle savedInstanceState) {}

    public void onStart() {}

    public void onResume() {}

    public void onPause() {}

    public void onStop() {}

    public void onRestart() {}

    public void onDestroy() {
        mActivity = null;
        mRootView = null;
    }

    /**
     * @return true if you want to shield back key
     */
    protected boolean onBackPressed() {
        return false;
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {}

    protected final <E extends View> E getView(int id) {
        try {
            return (E) mRootView.findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(UIBlock.class.getSimpleName(), "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }
}
