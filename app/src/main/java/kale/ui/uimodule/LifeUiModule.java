package kale.ui.uimodule;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * @author Kale
 * @date 2017/10/23
 */
public class LifeUiModule extends UiModule {

    private static final String TAG = "LifeUiModule";

    @Override
    protected int getLayoutResId() {
        return 0;
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
        Log.d(TAG, "onAttach() called with: activity = [" + activity + "]");
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState() called with: savedInstanceState = [" + savedInstanceState + "]");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
        outState.putString("name", "kale");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
