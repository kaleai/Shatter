package kale.ui.uiblock.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import android.content.Intent;
import android.os.Bundle;

import kale.ui.uiblock.UiBlockManager;
import kale.ui.uiblock.iface.UIBlockActivity;

/**
 * @author Kale
 * @date 2016/4/7
 */
@Aspect
public class UiBlockActivityAspect {

    @Pointcut("execution(* kale.ui.uiblock.iface.UIBlockActivity..on*(..))")
    public void onXXX() {
    }

    @After("onXXX()")
    public void callManagerMethods(JoinPoint point) {
        UIBlockActivity activity = (UIBlockActivity) point.getThis();
        UiBlockManager manager = activity.getUiBlockManager();
        if (manager == null) {
            return;
        }

        Object[] args = point.getArgs();
        switch (point.getSignature().getName()) {
            case "onSaveInstanceState":
                manager.onSaveInstanceState((Bundle) args[0]);
                break;
            case "onRestoreInstanceState":
                manager.onRestoreInstanceState((Bundle) args[0]);
                break;
            case "onStart":
                manager.onStart();
                break;
            case "onResume":
                manager.onResume();
                break;
            case "onPause":
                manager.onPause();
                break;
            case "onStop":
                manager.onStop();
                break;
            case "onRestart":
                manager.onRestart();
                break;
            case "onDestroy":
                manager.onDestroy();
                break;
            case "onBackPressed":
                manager.onBackPressed();
                break;
            case "onActivityResult":
                manager.onActivityResult(Integer.parseInt(args[0].toString()), 
                        Integer.parseInt(args[1].toString()), (Intent) args[2]);
                break;
        }
    }

}
