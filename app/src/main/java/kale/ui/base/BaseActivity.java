package kale.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import kale.ui.uiblock.ContainUIBlockActivity;
import kale.ui.uiblock.UiBlockManager;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class BaseActivity extends AppCompatActivity implements ContainUIBlockActivity{

    private UiBlockManager mUiBlockManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUiBlockManager = new UiBlockManager(this);
    }

    @Override
    public UiBlockManager getUiBlockManager() {
        return mUiBlockManager;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUiBlockManager.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!mUiBlockManager.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mUiBlockManager.onActivityResult(requestCode, resultCode, data);
    }

}
