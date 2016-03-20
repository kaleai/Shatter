package kale.ui.uiblock.support;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import kale.ui.uiblock.UiBlockManager;
import kale.ui.uiblock.iface.ContainUIBlockActivity;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class AppCompatUiBlockActivity extends AppCompatActivity implements ContainUIBlockActivity {

    private UiBlockManager mUiBlockManager;

    public UiBlockManager getUiBlockManager() {
        if (mUiBlockManager == null) {
            mUiBlockManager = new UiBlockManager(this);
        }
        return mUiBlockManager;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mUiBlockManager != null) {
            mUiBlockManager.onSaveInstanceState(outState);
        }
    }

    @CallSuper
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mUiBlockManager != null) {
            mUiBlockManager.onRestoreInstanceState(savedInstanceState);
        }
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        if (mUiBlockManager != null) {
            mUiBlockManager.onStart();
        }
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        if (mUiBlockManager != null) {
            mUiBlockManager.onResume();
        }
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        if (mUiBlockManager != null) {
            mUiBlockManager.onPause();
        }
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        if (mUiBlockManager != null) {
            mUiBlockManager.onStop();
        }
    }

    @CallSuper
    @Override
    public void onRestart() {
        super.onRestart();
        if (mUiBlockManager != null) {
            mUiBlockManager.onRestart();
        }
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUiBlockManager != null) {
            mUiBlockManager.onDestroy();
        }
    }

    @CallSuper
    @Override
    public void onBackPressed() {
        if (mUiBlockManager != null) {
            if (!mUiBlockManager.onBackPressed()) {
                super.onBackPressed();
            }
        }
    }

    @CallSuper
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mUiBlockManager != null) {
            mUiBlockManager.onActivityResult(requestCode, resultCode, data);
        }
    }

}
