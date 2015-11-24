package kale.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import kale.ui.uiblock.ContainUIBlockActivity;
import kale.ui.uiblock.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class BaseActivity extends AppCompatActivity implements ContainUIBlockActivity {

    private UIBlockManager mUIBlockManager;

    @Override
    public UIBlockManager getUIBlockManager() {
        if (mUIBlockManager == null) {
            mUIBlockManager = new UIBlockManager(this);
        }
        return mUIBlockManager;
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (mUIBlockManager != null) {
            mUIBlockManager.onSaveInstanceState(outState, outPersistentState);
        }
    }

    @CallSuper
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mUIBlockManager != null) {
            mUIBlockManager.onRestoreInstanceState(savedInstanceState);
        }
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        if (mUIBlockManager != null) {
            mUIBlockManager.onStart();
        }
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        if (mUIBlockManager != null) {
            mUIBlockManager.onResume();
        }
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        if (mUIBlockManager != null) {
            mUIBlockManager.onPause();
        }
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        if (mUIBlockManager != null) {
            mUIBlockManager.onStop();
        }
    }

    @CallSuper
    @Override
    public void onRestart() {
        super.onRestart();
        if (mUIBlockManager != null) {
            mUIBlockManager.onRestart();
        }
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUIBlockManager != null) {
            mUIBlockManager.onDestroy();
        }
    }
    
    @CallSuper
    @Override
    public void onBackPressed() {
        if (mUIBlockManager != null) {
            if (!mUIBlockManager.onBackPressed()) {
                super.onBackPressed();
            }
        }
    }

    @CallSuper
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mUIBlockManager != null) {
            mUIBlockManager.onActivityResult(requestCode, resultCode, data);
        }
    }

}
