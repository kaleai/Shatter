package kale.ui.uimodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import kale.ui.uimodule.lifecycle.Lifecycle;
import kale.ui.uimodule.lifecycle.ReportFragment;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jack Tony
 * @date 2015/6/15
 * 需要在界面销毁时把回调停止
 */
public abstract class UiModule implements Lifecycle {

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
        if (rootView instanceof ViewPager) {
            rootView = LayoutInflater.from(activity).inflate(getLayoutResId(), null);
        }
        rootView = resetRootView(rootView, activity);

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
     * 得到的{@link UiModuleManager}和当前容纳UiBlock的Activity中的{@link UiModuleManager}是同一个对象
     */
    public UiModuleManager getUiBlockManager() {
        return ((UiModuleActivity) activity).getUiModuleManager();
    }

    /**
     * 重置UiBlock的容器
     */
    @Deprecated
    protected View resetRootView(View oldRootView, Activity activity) {
        return oldRootView;
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
     * 定义后可通过{@link UiModuleManager#findUiBlockByTag(String)}来找到{@link UiModule}
     *
     * @return 自定义的tag，默认是当前类名
     */
    public String getTag() {
        return getClass().getSimpleName();
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        ReportFragment fragment = ReportFragment.get(activity);
        if (fragment != null) {
            fragment.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
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
        activity = null;
        rootView = null;
    }


    /**
     * findViewById的简化方法
     */
    protected final <E extends View> E getView(int id) {
        return rootView.findViewById(id);
    }

}
