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
        List<Shatter> blocks = manager.getShatters();
        switch (methodName) {
            case "onNewIntent":
                callBlocks(blocks, UiBlock -> UiBlock.onNewIntent((Intent) args[0]));
                break;
            case "onSaveInstanceState":
                callBlocks(blocks, UiBlock -> UiBlock.onSaveInstanceState((Bundle) args[0]));
                break;
            case "onRestoreInstanceState":
                callBlocks(blocks, UiBlock -> UiBlock.onRestoreInstanceState(((Bundle) args[0])));
                break;
            case "onStart":
                callBlocks(blocks, Shatter::onStart);
                break;
            case "onResume":
                callBlocks(blocks, Shatter::onResume);
                break;
            case "onPause":
                callBlocks(blocks, Shatter::onPause);
                break;
            case "onStop":
                callBlocks(blocks, Shatter::onStop);
                break;
            case "onRestart":
                callBlocks(blocks, Shatter::onRestart);
                break;
            case "onDestroy":
                callBlocks(blocks, Shatter::doDestroy);
                manager.onDestroy();
                break;
            case "onBackPressed":
                callBlocks(blocks, Shatter::onBackPressed);
                break;
            case "onActivityResult":
                callBlocks(blocks, UiBlock ->
                        UiBlock.onActivityResult(Integer.parseInt(args[0].toString()),
                                Integer.parseInt(args[1].toString()), (Intent) args[2]));
                break;
        }
    }

    private static void callBlocks(List<Shatter> blocks, final Callback callback) {
        for (int i = 0, size = blocks.size(); i < size; i++) {
            callback.onCall(blocks.get(i));
        }
    }

    private interface Callback {

        void onCall(Shatter UiModule);

    }
}
