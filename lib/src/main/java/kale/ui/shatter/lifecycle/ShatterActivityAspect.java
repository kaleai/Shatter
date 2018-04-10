package kale.ui.shatter.lifecycle;

import android.app.Activity;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import kale.ui.shatter.ShatterManager;
import kale.ui.shatter.IShatterHost;

/**
 * @author Kale
 * @date 2016/4/7
 */
@Aspect
/*package*/ public class ShatterActivityAspect {

    private String oldActivityName;

    private String oldMethod;

    @Pointcut("execution(* kale.ui.shatter.IShatterActivity..on*(..))")
    public void onXXXMethods() {
    }

    @After("onXXXMethods()")
    public void callManagerMethods(JoinPoint point) {
        IShatterHost activity = (IShatterHost) point.getThis();
        if (!(activity instanceof Activity)) {
            return;
        }
        String methodName = point.getSignature().getName();

        if (activity.toString().equals(oldActivityName) && methodName.equals(oldMethod)) {
            return;
        } else {
            oldActivityName = activity.toString();
            oldMethod = methodName;
        }

        ShatterManager manager = activity.getShatterManager();
        MethodExecutor.scheduleMethod(methodName, manager, point.getArgs());
    }

}
