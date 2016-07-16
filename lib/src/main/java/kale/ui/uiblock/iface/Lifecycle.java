package kale.ui.uiblock.iface;

import android.content.Intent;
import android.os.Bundle;

/**
 * @author Jack Tony
 * @date 2015/11/22
 */
public interface Lifecycle {

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
