package kale.ui.shatter;

import kale.ui.shatter.lifecycle.ActivityFullLifecycleCallbacks;

/**
 * @author Kale
 * @date 2016/4/9
 */
public interface ShatterOwner extends ActivityFullLifecycleCallbacks {

    ShatterManager getShatterManager();

}
