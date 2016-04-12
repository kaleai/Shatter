package kale.ui.uiblock;

import org.aspectj.lang.JoinPoint;

import android.app.Activity;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * @author Jack Tony
 * @date 2015/6/28
 */
public class UiBlockManager {

    private List<UiBlock> mUiBlockList = new ArrayList<>();

    @Getter
    protected final Activity activity;

    public UiBlockManager(@NonNull Activity activity) {
        this.activity = activity;
    }

    public UiBlockManager add(@IdRes int containViewId, @NonNull UiBlock block) {
        block.setContainId(containViewId);
        return add(activity.findViewById(containViewId), block);
    }

    public UiBlockManager add(@NonNull View containView, @NonNull UiBlock block) {
        block.setRootView(containView);
        block.attachActivity(activity);
        mUiBlockList.add(block);
        return this;
    }

    public void remove(@NonNull UiBlock block) {
        block.onDestroy();
        mUiBlockList.remove(block);
    }

    @CheckResult
    public
    @Nullable
    UiBlock findUiBlockByTag(@NonNull String tag) {
        for (UiBlock block : mUiBlockList) {
            if (tag.equals(block.getTag())) {
                return block;
            }
        }
        return null;
    }

    @CheckResult
    public
    @Nullable
    UiBlock findUiBlockByContainId(int id) {
        for (UiBlock block : mUiBlockList) {
            if (block.getContainId() == id) {
                return block;
            }
        }
        return null;
    }

    @CheckResult
    @NonNull
    public List<UiBlock> getUiBlockList() {
        return mUiBlockList;
    }

    /**
     * Call by {@link kale.ui.uiblock.aspect.UiBlockActivityAspect#callManagerMethods(JoinPoint)}
     */
    public void onDestroy() {
        mUiBlockList.clear();
        mUiBlockList = null;
    }

}