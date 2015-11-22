package kale.ui.uiblock;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Tony
 * @date 2015/6/28
 */
public class UiBlockManager {

    private List<UiBlock> mUiBlockList;

    protected Activity activity;
    
    public UiBlockManager(@NonNull ContainUIBlockActivity activity) {
        this.activity = (Activity) activity;
    }

    public <T extends ContainUIBlockActivity> UiBlockManager add(@NonNull UiBlock<T> uiBlock) {
        uiBlock.attachActivity((T) activity);
        if (mUiBlockList == null) {
            mUiBlockList = new ArrayList<>();
        }
        mUiBlockList.add(uiBlock);
        return this;
    }

    public UiBlockManager remove(@NonNull UiBlock uiBlock) {
        uiBlock.onDestroy();
        if (mUiBlockList != null && mUiBlockList.contains(uiBlock)) {
            mUiBlockList.remove(uiBlock);
        }
        return this;
    }

    public List<UiBlock> getUIblocks() {
        return mUiBlockList;
    }
    
    @CheckResult
    public <T extends UiBlock> T get(@NonNull Class<T> cls) {
        if (mUiBlockList != null) {
            for (int i = 0, size = mUiBlockList.size(); i < size; i++) {
                if (mUiBlockList.get(i).getClass().getCanonicalName().equals(cls.getCanonicalName())) {
                    return (T) mUiBlockList.get(i);
                }
            }
        }
        return null;
    }

    @CheckResult
    public List<UiBlock> getUiBlockList() {
        return mUiBlockList;
    }
    
    
    /// 回调 start -------------------
    
    public boolean onBackPressed() {
        boolean handled = false;
        if (mUiBlockList != null) {
            for (int i = 0, size = mUiBlockList.size(); i < size; i++) {
                handled = mUiBlockList.get(i).onBackPressed();
                if (handled) {
                    break;
                }
            }
        }
        return handled;
    }

    public void onDestroy() {
        if (mUiBlockList != null) {
            callBlock(new Callback() {
                @Override
                public void onCall(int i) {
                    mUiBlockList.get(i).onDestroy();
                }
            });
            mUiBlockList.clear();
            mUiBlockList = null;
        }
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        callBlock(new Callback() {
            @Override
            public void onCall(int i) {
                mUiBlockList.get(i).onActivityResult(requestCode, resultCode, data);
            }
        });
    }
    
    //// 回调 end -------------------

    private void callBlock(final Callback callback) {
        if (mUiBlockList != null) {
            for (int i = 0, size = mUiBlockList.size(); i < size; i++) {
                callback.onCall(i);
            }
        }
    }

    public Activity getActivity() {
        return activity;
    }

    private interface Callback {

        void onCall(int i);
        
    }
}