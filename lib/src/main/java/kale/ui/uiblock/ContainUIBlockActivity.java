package kale.ui.uiblock;

import kale.ui.uiblock.iface.ActivityLifecycle;

/**
 * @author Jack Tony
 * @date 2015/10/8
 */
public interface ContainUIBlockActivity extends ActivityLifecycle {

    UIBlockManager getUIBlockManager();
    
}
