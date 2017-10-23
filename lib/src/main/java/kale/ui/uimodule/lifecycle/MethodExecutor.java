package kale.ui.uimodule.lifecycle;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;

import kale.ui.uimodule.UiModule;
import kale.ui.uimodule.UiModuleManager;

/**
 * @author Kale
 * @date 2017/10/23
 */
public class MethodExecutor {

    public static void scheduleMethod(String methodName, UiModuleManager manager, Object[] args) {
        if (manager == null) {
            return;
        }
        List<UiModule> blocks = manager.getUiModuleList();
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
                callBlocks(blocks, UiModule::onStart);
                break;
            case "onResume":
                callBlocks(blocks, UiModule::onResume);
                break;
            case "onPause":
                callBlocks(blocks, UiModule::onPause);
                break;
            case "onStop":
                callBlocks(blocks, UiModule::onStop);
                break;
            case "onRestart":
                callBlocks(blocks, UiModule::onRestart);
                break;
            case "onDestroy":
                callBlocks(blocks, UiModule::doDestroy);
                manager.onDestroy();
                break;
            case "onBackPressed":
                callBlocks(blocks, UiModule::onBackPressed);
                break;
            case "onActivityResult":
                callBlocks(blocks, UiBlock ->
                        UiBlock.onActivityResult(Integer.parseInt(args[0].toString()),
                                Integer.parseInt(args[1].toString()), (Intent) args[2]));
                break;
        }
    }

    private static void callBlocks(List<UiModule> blocks, final Callback callback) {
        for (int i = 0, size = blocks.size(); i < size; i++) {
            callback.onCall(blocks.get(i));
        }
    }

    private interface Callback {

        void onCall(UiModule UiModule);

    }
}
