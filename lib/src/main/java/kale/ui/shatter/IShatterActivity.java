package kale.ui.shatter;

import android.content.Intent;
import android.os.Bundle;

/**
 * @author Kale
 * @date 2017/10/31
 */
public interface IShatterActivity extends IShatterHost {

    void onNewIntent(Intent intent);

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onRestart();

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onBackPressed();
}
