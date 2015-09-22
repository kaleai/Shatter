package kale.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kale.ui.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class BaseActivity extends AppCompatActivity {

    private UIBlockManager mUIBlockManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUIBlockManager = new UIBlockManager(this);
    }

    public UIBlockManager getUIBlockManager() {
        return mUIBlockManager;
    }

    @Override
    public void onBackPressed() {
        boolean handled = mUIBlockManager.onBackPressed();
        if (!handled) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUIBlockManager.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mUIBlockManager.onActivityResult(requestCode, resultCode, data);
    }

}
