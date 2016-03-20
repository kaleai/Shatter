package kale.ui.uiblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import kale.ui.uiblock.iface.ActivityLifecycle;
import kale.ui.uiblock.iface.ContainUIBlockActivity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jack Tony
 * @date 2015/6/15
 * 1.需要在界面销毁时把回调停止
 */
public abstract class UiBlock implements ActivityLifecycle {

    @Getter@Setter
    private View rootView;

    @Getter
    private Activity activity;

    // 当前是否对用户可见
    @Getter
    private boolean visibleToUser = true;
    
    protected void attachActivity(Activity activity) {
        onAttach(activity);
        if (rootView instanceof ViewPager) {
            rootView = LayoutInflater.from(activity).inflate(getLayoutResId(), null);
        }
        rootView = resetRootView(rootView, activity);
        
        bindViews(rootView);
        beforeSetViews();
        setViews();
    }

    protected void onAttach(Activity activity) {
        this.activity = activity;
    }

    // @formatter:off
    /**
     * 仅仅是为了查找问题方便而强制书写的
     *
     * @return uiBlock对应的layout文件id
     */
    protected abstract @LayoutRes int getLayoutResId();
    protected abstract void bindViews(View rootView);
    protected void beforeSetViews() {}
    protected abstract void setViews();
     /**
     * 在viewpager中更新数据时才会用到的方法，一般情况下不需要使用
     */
    public void handleData(Object model, int position) {}
    // @formatter:on

    protected View resetRootView(View oldRootView, Activity activity) {
        return oldRootView;
    }

    public UiBlockManager getManager() {
        return ((ContainUIBlockActivity) activity).getUiBlockManager();
    }

    public void onVisibleToUser(boolean isVisible) {
        visibleToUser = isVisible;
    }

    public String getTag() {
        return getClass().getSimpleName();
    }

    // @formatter:off
    /**
     * @return true if you want to shield back key
     */
    protected boolean onBackPressed() {return false;}
    public void onSaveInstanceState(Bundle outState) {}
    public void onRestoreInstanceState(Bundle savedInstanceState) {}
    public void onStart() {}
    public void onResume() {}
    public void onPause() {}
    public void onStop() {}
    public void onRestart() {}
    public void onActivityResult(int requestCode, int resultCode, Intent data) {}
    // @formatter:on

    public void onDestroy() {
        activity = null;
        rootView = null;
    }

    protected final <E extends View> E getView(int id) {
        try {
            return (E) rootView.findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(UiBlock.class.getSimpleName(), "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

}
