package kale.ui.uimodule;

import kale.ui.uimodule.lifecycle.Lifecycle;

/**
 * @author Kale
 * @date 2016/4/9
 */
public interface UiModuleActivity extends Lifecycle {

    UiModuleManager getUiModuleManager();

}
