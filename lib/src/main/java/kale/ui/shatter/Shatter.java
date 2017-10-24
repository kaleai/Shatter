package kale.ui.shatter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.shatter.lifecycle.ActivityFullLifecycleCallbacks;
import kale.ui.shatter.lifecycle.EventDispatchFragment;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jack Tony
 * @date 2015/6/15
 * 需要在界面销毁时把回调停止
 */
public abstract class Shatter implements ActivityFullLifecycleCallbacks {

    public static final int NO_LAYOUT = 0;

    @Getter
    @Setter
    private View rootView;

    @Getter
    private Activity activity;

    /**
     * 当前是否对用户可见
     */
    @Getter
    private boolean visibleToUser = true;

    @Setter
    @Getter
    private int containId;

    protected void attachActivity(Activity activity) {
        onAttach(activity);

        if (getLayoutResId() != NO_LAYOUT) {
            if (rootView instanceof ViewPager) {
                rootView = LayoutInflater.from(activity).inflate(getLayoutResId(), null);
            } else if (rootView instanceof ViewGroup) {
                View view = LayoutInflater.from(activity).inflate(getLayoutResId(), null);
                ((ViewGroup) rootView).addView(view);
            } else {
                throw new IllegalArgumentException("ContainView must extends ViewGroup");
            }
        }
        onCreate();
    }

    protected void onCreate() {
        bindViews(rootView);
        onViewCreated();
        setViews();
    }

    /**
     * 被挂载到Activity时的回调方法
     */
    protected void onAttach(Activity activity) {
        this.activity = activity;
    }

    /**
     * 得到的{@link ShatterManager}和当前容纳UiBlock的Activity中的{@link ShatterManager}是同一个对象
     */
    public ShatterManager getShatterManager() {
        return ((ShatterOwner) activity).getShatterManager();
    }

    /**
     * 对用户可见的时的回调，可用作懒加载<br>
     *
     * @param isVisible 是否可见
     */
    public void onVisibleToUser(boolean isVisible) {
        visibleToUser = isVisible;
    }

    /**
     * 定义后可通过{@link ShatterManager#findShatterByTag(String)}来找到{@link Shatter}
     *
     * @return 自定义的tag，默认是当前类名
     */
    public String getTag() {
        return getClass().getSimpleName();
    }

    // @formatter:off
    /**
     * @return uiBlock对应的layout文件id
     */
    protected abstract @LayoutRes int getLayoutResId();
    protected abstract void bindViews(View rootView);
    protected void onViewCreated() {}
    protected abstract void setViews();
     /**
     * 在viewpager中更新数据时才会用到的方法，一般情况下不需要使用
     */
    public void handleData(Object model, int position) {}
    
    // --- life ---
    @Override public void onSaveInstanceState(Bundle outState) {}
    @Override public void onRestoreInstanceState(Bundle savedInstanceState) {}
    @Override public void onStart() {}
    @Override public void onResume() {}
    @Override public void onPause() {}
    @Override public void onStop() {}
    @Override public void onDestroy() {}
    @Override public void onRestart() {}
    @Override public void onBackPressed() {}
    @Override public void onNewIntent(Intent intent) {}
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {}
    // @formatter:on

    public void doDestroy() {
        onDestroy();
        // after onDestroy()
        activity = null;
        rootView = null;
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        EventDispatchFragment fragment = EventDispatchFragment.get(activity);
        if (fragment != null) {
            fragment.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * findViewById的简化方法
     */
    protected final <E extends View> E findViewById(int id) {
        return rootView.findViewById(id);
    }

    public void startActivity(Intent intent) {
        activity.startActivity(intent);
    }

}
