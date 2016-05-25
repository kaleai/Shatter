package kale.ui.uiblock.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import kale.ui.uiblock.UiBlock;
import kale.ui.uiblock.UiBlockManager;
import kale.ui.uiblock.iface.UiBlockActivity;

/**
 * @author Kale
 * @date 2016/4/7
 */
@Aspect
/*package*/ public class UiBlockActivityAspect {

    @Pointcut("execution(* kale.ui.uiblock.iface.UiBlockActivity..on*(..))")
    public void on$() {
    }

    @After("on$()")
    public void callManagerMethods(JoinPoint point) {
        UiBlockActivity activity = (UiBlockActivity) point.getThis();
        UiBlockManager manager = activity.getInternalManager();
        if (manager == null) {
            return;
        }

        List<UiBlock> blocks = manager.getUiBlockList();

        Object[] args = point.getArgs();
        switch (point.getSignature().getName()) {
            case "onSaveInstanceState":
                callBlocks(blocks, UiBlock -> UiBlock.onSaveInstanceState((Bundle) args[0]));
                break;
            case "onRestoreInstanceState":
                callBlocks(blocks, UiBlock -> UiBlock.onRestoreInstanceState(((Bundle) args[0])));
                break;
            case "onStart":
                callBlocks(blocks, UiBlock::onStart);
                break;
            case "onResume":
                callBlocks(blocks, UiBlock::onResume);
                break;
            case "onPause":
                callBlocks(blocks, UiBlock::onPause);
                break;
            case "onStop":
                callBlocks(blocks, UiBlock::onStop);
                break;
            case "onRestart":
                callBlocks(blocks, UiBlock::onRestart);
                break;
            case "onDestroy":
                callBlocks(blocks, UiBlock::onDestroy);
                manager.onDestroy();
                break;
            case "onBackPressed":
                callBlocks(blocks, UiBlock::onBackPressed);
                break;
            case "onActivityResult":
                callBlocks(blocks, UiBlock ->
                        UiBlock.onActivityResult(Integer.parseInt(args[0].toString()),
                                Integer.parseInt(args[1].toString()), (Intent) args[2]));
                break;
        }
    }

    private void callBlocks(List<UiBlock> blocks, final Callback callback) {
        for (int i = 0, size = blocks.size(); i < size; i++) {
            callback.onCall(blocks.get(i));
        }
    }

    private interface Callback {

        void onCall(UiBlock UiBlock);

    }

}
