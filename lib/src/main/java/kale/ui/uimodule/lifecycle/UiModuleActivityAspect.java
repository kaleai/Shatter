package kale.ui.uimodule.lifecycle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import kale.ui.uimodule.UiModuleActivity;
import kale.ui.uimodule.UiModuleManager;

/**
 * @author Kale
 * @date 2016/4/7
 */
@Aspect
/*package*/ public class UiModuleActivityAspect {

    private String oldActivityName;

    private String oldMethod;
    
    @Pointcut("execution(* kale.ui.uimodule.UiModuleActivity..on*(..))")
    public void on$() {
    }

    @After("on$()")
    public void callManagerMethods(JoinPoint point) {
        UiModuleActivity activity = (UiModuleActivity) point.getThis();
        String methodName = point.getSignature().getName();

        if (activity.toString().equals(oldActivityName) && methodName.equals(oldMethod)) {
            return;
        } else {
            oldActivityName = activity.toString();
            oldMethod = methodName;
        }

        UiModuleManager manager = activity.getUiModuleManager();
        MethodExecutor.scheduleMethod(methodName, manager, point.getArgs());
    }

}
