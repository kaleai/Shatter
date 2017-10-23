package kale.ui.uimodule;

import org.aspectj.lang.JoinPoint;

import android.app.Activity;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kale.ui.uimodule.lifecycle.UiModuleActivityAspect;
import lombok.Getter;

/**
 * @author Jack Tony
 * @date 2015/6/28
 */
public class UiModuleManager {

    private List<UiModule> mUiModuleList = new ArrayList<>();

    @Getter
    protected final Activity activity;

    public UiModuleManager(@NonNull Activity activity) {
        this.activity = activity;
    }

    public UiModuleManager add(@IdRes int containViewId, @NonNull UiModule block) {
        block.setContainId(containViewId);
        return add(activity.findViewById(containViewId), block);
    }

    public UiModuleManager add(@NonNull View containView, @NonNull UiModule block) {
        block.setRootView(containView);
        block.attachActivity(activity);
        mUiModuleList.add(block);
        return this;
    }

    public void remove(@NonNull UiModule block) {
        block.onDestroy();
        mUiModuleList.remove(block);
    }

    @CheckResult
    public
    @Nullable
    UiModule findUiBlockByTag(@NonNull String tag) {
        for (UiModule block : mUiModuleList) {
            if (tag.equals(block.getTag())) {
                return block;
            }
        }
        return null;
    }

    @CheckResult
    public
    @Nullable
    UiModule findUiBlockByContainId(int id) {
        for (UiModule block : mUiModuleList) {
            if (block.getContainId() == id) {
                return block;
            }
        }
        return null;
    }

    @CheckResult
    @NonNull
    public List<UiModule> getUiModuleList() {
        return mUiModuleList;
    }

    /**
     * Call by {@link UiModuleActivityAspect#callManagerMethods(JoinPoint)}
     */
    public void onDestroy() {
        mUiModuleList.clear();
        mUiModuleList = null;
    }

}