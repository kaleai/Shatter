package kale.ui.uiblock.iface;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * @author Jack Tony
 * @date 2015/11/22
 */
public interface ActivityLifecycle {

    void onDestroy();

    void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onRestart();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
