package kale.ui.uiblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kale.ui.uiblock.iface.ActivityLifecycle;
import lombok.Getter;

/**
 * @author Jack Tony
 * @date 2015/6/28
 */
public class UiBlockManager implements ActivityLifecycle {

    private List<UiBlock> mUiBlockList = new ArrayList<>();

    @Getter
    protected final Activity activity;

    public UiBlockManager(@NonNull Activity activity) {
        this.activity = activity;
    }

    public UiBlockManager add(@IdRes int containViewId, @NonNull UiBlock block) {
        return add(activity.findViewById(containViewId), block);
    }

    public UiBlockManager add(@NonNull View containView, @NonNull UiBlock block) {
        block.setRootView(containView);
        block.attachActivity(activity);
        mUiBlockList.add(block);
        return this;
    }

    public UiBlockManager remove(@NonNull UiBlock block) {
        block.onDestroy();
        if (mUiBlockList.contains(block)) {
            mUiBlockList.remove(block);
        }
        return this;
    }

    @CheckResult
    public
    @Nullable
    UiBlock get(@Nullable String tag) {
        if (tag == null) {
            return null;
        } else {
            for (UiBlock block : mUiBlockList) {
                if (TextUtils.equals(block.getTag(), tag)) {
                    return block;
                }
            }
            return null;
        }
    }

    @CheckResult
    @NonNull
    public List<UiBlock> getUiBlockList() {
        return mUiBlockList;
    }

    /// 回调 start -------------------

    public void onSaveInstanceState(final Bundle outState) {
        callUiBlock(block -> block.onSaveInstanceState(outState));
    }

    public void onRestoreInstanceState(final Bundle savedInstanceState) {
        callUiBlock(block -> block.onRestoreInstanceState(savedInstanceState));
    }

    public void onStart() {
        callUiBlock(UiBlock::onStart);
    }

    public void onResume() {
        callUiBlock(UiBlock::onResume);
    }

    public void onPause() {
        callUiBlock(UiBlock::onPause);
    }

    public void onStop() {
        callUiBlock(UiBlock::onStop);
    }

    public void onRestart() {
        callUiBlock(UiBlock::onRestart);
    }

    public void onDestroy() {
        callUiBlock(UiBlock::onDestroy);
        mUiBlockList.clear();
        mUiBlockList = null;
    }

    public boolean onBackPressed() {
        for (UiBlock UiBlock : mUiBlockList) {
            if (UiBlock.onBackPressed()) {
                return true;
            }
        }
        return false;
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        callUiBlock(UiBlock -> UiBlock.onActivityResult(requestCode, resultCode, data));
    }

    //// 回调 end -------------------

    private void callUiBlock(final Callback callback) {
        for (int i = 0, size = mUiBlockList.size(); i < size; i++) {
            callback.onCall(mUiBlockList.get(i));
        }
    }

    private interface Callback {

        void onCall(UiBlock UiBlock);

    }
}