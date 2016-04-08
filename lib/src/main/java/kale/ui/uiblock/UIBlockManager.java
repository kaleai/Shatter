package kale.ui.uiblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kale.ui.uiblock.iface.Lifecycle;
import lombok.Getter;

/**
 * @author Jack Tony
 * @date 2015/6/28
 */
public class UiBlockManager implements Lifecycle {

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

    public void onBackPressed() {
        callUiBlock(UiBlock::onBackPressed);
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        callUiBlock(UiBlock -> UiBlock.onActivityResult(requestCode, resultCode, data));
    }

    public void onDestroy() {
        callUiBlock(UiBlock::onDestroy);
        mUiBlockList.clear();
        mUiBlockList = null;
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