package kale.ui.shatter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.shatter.lifecycle.EventDispatchFragment;

/**
 * @author Jack Tony
 * @date 2015/6/15
 * 需要在界面销毁时把回调停止
 */
public abstract class Shatter implements IShatterOwner {

    public static final int NO_LAYOUT = 0;

    private View rootView;

    private Activity activity;

    /**
     * 当前是否对用户可见
     */
    private boolean visibleToUser = true;

    private int containId;

    protected void attachActivity(Activity activity) {
        onAttach(activity);

        if (getLayoutResId() != NO_LAYOUT) {
            if (rootView instanceof ViewPager) {
                rootView = LayoutInflater.from(activity).inflate(getLayoutResId(), null);
            } else if (rootView instanceof ViewGroup) {
                View view = LayoutInflater.from(activity).inflate(getLayoutResId(), null);
                ((ViewGroup) rootView).addView(view);
                rootView = view;
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
     * 得到的{@link ShatterManager}和当前Activity中的{@link ShatterManager}是同一个对象
     */
    public ShatterManager getShatterManager() {
        return ((IShatterOwner) activity).getShatterManager();
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
    protected abstract @LayoutRes int getLayoutResId();
    protected abstract void bindViews(View rootView);
    protected void onViewCreated() {}
    protected abstract void setViews();
    
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

    protected final <E extends View> E findViewById(int id) {
        return rootView.findViewById(id);
    }

    public void startActivity(Intent intent) {
        activity.startActivity(intent);
    }

    public Activity getActivity() {
        return activity;
    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public boolean isVisibleToUser() {
        return visibleToUser;
    }

    public void setContainId(int containId) {
        this.containId = containId;
    }

    public int getContainId() {
        return containId;
    }
}
