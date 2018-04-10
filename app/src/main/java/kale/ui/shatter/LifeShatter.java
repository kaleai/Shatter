package kale.ui.shatter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * @author Kale
 * @date 2017/10/23
 */
public class LifeShatter extends Shatter {

    private static final String TAG = "LifeShatter";

    @Override
    protected int getLayoutResId() {
        return NO_LAYOUT;
    }

    @Override
    protected void bindViews(View rootView) {

    }

    @Override
    protected void setViews() {

    }

    @Override
    protected void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach() called with: mActivity = [" + activity + "]");
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
    }

    @Override
    public void onActStart() {
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onActResume() {
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onActPause() {
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onActStop() {
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onActRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState() called with: savedInstanceState = [" + savedInstanceState + "]");
    }

    @Override
    public void onActSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
        outState.putString("name", "kale");
    }

    @Override
    public void onActDestroy() {
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
    }
}
