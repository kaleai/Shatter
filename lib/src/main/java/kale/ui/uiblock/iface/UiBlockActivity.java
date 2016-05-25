package kale.ui.uiblock.iface;

import kale.ui.uiblock.UiBlockManager;

/**
 * @author Kale
 * @date 2016/4/9
 */
public interface UiBlockActivity extends Lifecycle{

    UiBlockManager getUiBlockManager();

    UiBlockManager getInternalManager();
}
