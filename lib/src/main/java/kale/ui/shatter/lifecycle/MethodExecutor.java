package kale.ui.shatter.lifecycle;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;

import kale.ui.shatter.Shatter;
import kale.ui.shatter.ShatterManager;

/**
 * @author Kale
 * @date 2017/10/23
 */
public class MethodExecutor {

    public static void scheduleMethod(String methodName, ShatterManager manager, Object[] args) {
        if (manager == null) {
            return;
        }
        List<Shatter> shatters = manager.getShatters();
        switch (methodName) {
            case "onNewIntent":
                callShatterFunc(shatters, shatter -> shatter.onNewIntent((Intent) args[0]));
                break;
            case "onSaveInstanceState":
                callShatterFunc(shatters, shatter -> shatter.onSaveInstanceState((Bundle) args[0]));
                break;
            case "onRestoreInstanceState":
                callShatterFunc(shatters, shatter -> shatter.onRestoreInstanceState(((Bundle) args[0])));
                break;
            case "onStart":
                callShatterFunc(shatters, Shatter::onStart);
                break;
            case "onResume":
                callShatterFunc(shatters, Shatter::onResume);
                break;
            case "onPause":
                callShatterFunc(shatters, Shatter::onPause);
                break;
            case "onStop":
                callShatterFunc(shatters, Shatter::onStop);
                break;
            case "onRestart":
                callShatterFunc(shatters, Shatter::onRestart);
                break;
            case "onDestroy":
                callShatterFunc(shatters, Shatter::doDestroy);
                manager.onDestroy();
                break;
            case "onBackPressed":
                callShatterFunc(shatters, Shatter::onBackPressed);
                break;
            case "onActivityResult":
                callShatterFunc(shatters, shatter ->
                        shatter.onActivityResult(Integer.parseInt(args[0].toString()),
                                Integer.parseInt(args[1].toString()), (Intent) args[2]));
                break;
        }
    }

    private static void callShatterFunc(List<Shatter> shatters, final Callback callback) {
        for (int i = 0, size = shatters.size(); i < size; i++) {
            callback.onCall(shatters.get(i));
        }
    }

    private interface Callback {

        void onCall(Shatter UiModule);

    }
}
