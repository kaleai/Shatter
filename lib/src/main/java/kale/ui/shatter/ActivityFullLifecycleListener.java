package kale.ui.shatter;

import android.content.Intent;
import android.os.Bundle;

/**
 * @author Jack Tony
 * @date 2015/11/22
 */
public interface ActivityFullLifecycleListener {

    void onActNewIntent(Intent intent);

    void onActSaveInstanceState(Bundle outState);

    void onActRestoreInstanceState(Bundle savedInstanceState);

    void onActStart();

    void onActResume();

    void onActPause();

    void onActStop();

    void onActRestart();

    void onActDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onActBackPressed();
}
