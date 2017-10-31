package kale.ui.shatter.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import kale.ui.shatter.IShatterOwner;
import kale.ui.shatter.ShatterManager;

/**
 * @author Kale
 * @date 2017/10/23
 */

class Event {

    static String ON_CREATE = "onRestoreInstanceState",
            ON_START = "onStart",
            ON_RESUME = "onResume",
            ON_PAUSE = "onPause",
            ON_STOP = "onStop",
            ON_DESTROY = "onDestroy",
            ON_ACTIVITY_RESULT = "onActivityResult",
            ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
}

public class EventDispatchFragment extends android.app.Fragment {

    private static final String REPORT_FRAGMENT_TAG = "KALE_UI_SHATTER_EVENT_FRAGMENT_TAG";

    public static void injectIfNeededIn(Activity activity) {
        // ProcessLifecycleOwner should always correctly work and some activities may not extend
        // FragmentActivity from support lib, so we use framework fragments for activities
        android.app.FragmentManager manager = activity.getFragmentManager();
        if (manager.findFragmentByTag(REPORT_FRAGMENT_TAG) == null) {
            manager.beginTransaction().add(new EventDispatchFragment(), REPORT_FRAGMENT_TAG).commit();
            // Hopefully, we are the first to make a transaction.
            manager.executePendingTransactions();
        }
    }

    public static EventDispatchFragment get(Activity activity) {
        return (EventDispatchFragment) activity.getFragmentManager().findFragmentByTag(REPORT_FRAGMENT_TAG);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        dispatch(Event.ON_SAVE_INSTANCE_STATE, outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dispatch(Event.ON_CREATE, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        dispatch(Event.ON_START);
    }

    @Override
    public void onResume() {
        super.onResume();
        dispatch(Event.ON_RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        dispatch(Event.ON_PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        dispatch(Event.ON_STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispatch(Event.ON_DESTROY);
    }

    /**
     * 仅仅当前fragment启动activity的时候才会触发此回调
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dispatch(Event.ON_ACTIVITY_RESULT, requestCode, resultCode, data);
    }

    private void dispatch(String event) {
        dispatch(event, 1);
    }

    private void dispatch(String event, Object... args) {
        Activity activity = getActivity();
        if (activity instanceof IShatterOwner) {
            ShatterManager manager = ((IShatterOwner) activity).getShatterManager();
            MethodExecutor.scheduleMethod(event, manager, args);
        }
    }

}
