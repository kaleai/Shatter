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
public class UIBlockManager {

    private List<UIBlock> mUIBlockList;

    protected Activity activity;
    
    public UIBlockManager(@NonNull ContainUIBlockActivity activity) {
        this.activity = (Activity) activity;
    }

    public <T extends ContainUIBlockActivity> UIBlockManager add(@NonNull UIBlock<T> UIBlock) {
        UIBlock.attachActivity((T) activity);
        if (mUIBlockList == null) {
            mUIBlockList = new ArrayList<>();
        }
        mUIBlockList.add(UIBlock);
        return this;
    }

    public UIBlockManager remove(@NonNull UIBlock UIBlock) {
        UIBlock.onDestroy();
        if (mUIBlockList != null && mUIBlockList.contains(UIBlock)) {
            mUIBlockList.remove(UIBlock);
        }
        return this;
    }

    public List<UIBlock> getUIblocks() {
        return mUIBlockList;
    }
    
    @CheckResult
    public <T extends UIBlock> T get(@NonNull Class<T> cls) {
        if (mUIBlockList != null) {
            for (int i = 0, size = mUIBlockList.size(); i < size; i++) {
                if (mUIBlockList.get(i).getClass().getCanonicalName().equals(cls.getCanonicalName())) {
                    return (T) mUIBlockList.get(i);
                }
            }
        }
        return null;
    }

    @CheckResult
    public List<UIBlock> getUiBlockList() {
        return mUIBlockList;
    }
    
    
    /// 回调 start -------------------
    
    public boolean onBackPressed() {
        boolean handled = false;
        if (mUIBlockList != null) {
            for (int i = 0, size = mUIBlockList.size(); i < size; i++) {
                handled = mUIBlockList.get(i).onBackPressed();
                if (handled) {
                    break;
                }
            }
        }
        return handled;
    }

    public void onDestroy() {
        if (mUIBlockList != null) {
            callBlock(new Callback() {
                @Override
                public void onCall(int i) {
                    mUIBlockList.get(i).onDestroy();
                }
            });
            mUIBlockList.clear();
            mUIBlockList = null;
        }
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        callBlock(new Callback() {
            @Override
            public void onCall(int i) {
                mUIBlockList.get(i).onActivityResult(requestCode, resultCode, data);
            }
        });
    }
    
    //// 回调 end -------------------

    private void callBlock(final Callback callback) {
        if (mUIBlockList != null) {
            for (int i = 0, size = mUIBlockList.size(); i < size; i++) {
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