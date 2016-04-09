package kale.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kale.ui.uiblock.UiBlockManager;
import kale.ui.uiblock.iface.UIBlockActivity;

/**
 * @author Kale
 * @date 2016/4/8
 */
public class BaseActivity extends AppCompatActivity implements UIBlockActivity {

    private UiBlockManager mUiBlockManager;

    @Override
    public UiBlockManager getUiBlockManager() {
        if (mUiBlockManager == null) {
            mUiBlockManager = new UiBlockManager(this);
        }
        return mUiBlockManager;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
