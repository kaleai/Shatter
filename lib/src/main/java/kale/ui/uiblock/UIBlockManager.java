package kale.ui.uiblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import kale.ui.uiblock.iface.ActivityLifecycle;

/**
 * @author Jack Tony
 * @date 2015/6/28
 */
public class UIBlockManager implements ActivityLifecycle{

    private List<UIBlock> mUIBlockList;

    protected Activity activity;
    
    public UIBlockManager(@NonNull Activity activity) {
        this.activity = activity;
        mUIBlockList = new ArrayList<>();
    }

    public <T extends ContainUIBlockActivity> UIBlockManager add(@NonNull UIBlock<T> UIBlock) {
        UIBlock.attachActivity((T) activity);
        mUIBlockList.add(UIBlock);
        return this;
    }

    public UIBlockManager remove(@NonNull UIBlock UIBlock) {
        UIBlock.onDestroy();
        if (mUIBlockList.contains(UIBlock)) {
            mUIBlockList.remove(UIBlock);
        }
        return this;
    }

    @CheckResult
    public <T extends UIBlock> T get(@NonNull Class<T> cls) {
        for (int i = 0, size = mUIBlockList.size(); i < size; i++) {
            if (mUIBlockList.get(i).getClass().getCanonicalName().equals(cls.getCanonicalName())) {
                return (T) mUIBlockList.get(i);
            }
        }
        return null;
    }

    @CheckResult
    @NonNull
    public List<UIBlock> getUIBlockList() {
        return mUIBlockList;
    }
    
    
    /// 回调 start -------------------

    public void onSaveInstanceState(final Bundle outState, final PersistableBundle outPersistentState) {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onSaveInstanceState(outState, outPersistentState);
            }
        });
    }

    public void onRestoreInstanceState(final Bundle savedInstanceState) {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onRestoreInstanceState(savedInstanceState);
            }
        });
    }

    public void onStart() {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onStart();
            }
        });
    }

    public void onResume() {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onResume();
            }
        });
    }

    public void onPause() {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onPause();
            }
        });
    }

    public void onStop() {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onStop();
            }
        });
    }

    public void onRestart() {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onRestart();
            }
        });
    }
    
    public void onDestroy() {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onDestroy();
            }
        });
        mUIBlockList.clear();
        mUIBlockList = null;
    }

    public boolean onBackPressed() {
        for (UIBlock UIBlock : mUIBlockList) {
            if (UIBlock.onBackPressed()) {
                return true;
            }
        }
        return false;
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        callBlock(new Callback() {
            @Override
            public void onCall(UIBlock UIBlock) {
                UIBlock.onActivityResult(requestCode, resultCode, data);
            }
        });
    }
    
    //// 回调 end -------------------

    private void callBlock(final Callback callback) {
        for (int i = 0, size = mUIBlockList.size(); i < size; i++) {
            callback.onCall(mUIBlockList.get(i));
        }
    }

    public Activity getActivity() {
        return activity;
    }

    private interface Callback {

        void onCall(UIBlock UIBlock);
        
    }
}