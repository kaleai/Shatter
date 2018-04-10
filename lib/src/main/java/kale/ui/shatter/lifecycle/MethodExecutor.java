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
                callShatterFunc(shatters, shatter -> shatter.onActNewIntent((Intent) args[0]));
                break;
            case Event.ON_SAVE_INSTANCE_STATE:
                callShatterFunc(shatters, shatter -> shatter.onActSaveInstanceState((Bundle) args[0]));
                break;
            case "onRestoreInstanceState":
                callShatterFunc(shatters, shatter -> shatter.onActRestoreInstanceState(((Bundle) args[0])));
                break;
            case Event.ON_START:
                callShatterFunc(shatters, Shatter::onActStart);
                break;
            case Event.ON_RESUME:
                callShatterFunc(shatters, Shatter::onActResume);
                break;
            case Event.ON_PAUSE:
                callShatterFunc(shatters, Shatter::onActPause);
                break;
            case Event.ON_STOP:
                callShatterFunc(shatters, Shatter::onActStop);
                break;
            case "onRestart":
                callShatterFunc(shatters, Shatter::onActRestart);
                break;
            case Event.ON_DESTROY:
                callShatterFunc(shatters, Shatter::doDestroy);
                manager.destroy();
                break;
            case "onBackPressed":
                callShatterFunc(shatters, Shatter::onActBackPressed);
                break;
            case Event.ON_ACTIVITY_RESULT:
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
