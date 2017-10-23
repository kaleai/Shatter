package kale.ui.uimodule.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.uimodule.UiModule;
import kale.ui.uimodule.UiModuleManager;

/**
 * @author Jack Tony
 * @date 2015/11/21
 * 这个类不关心缓存，仅仅做一般的操作。比如得到view，返回view
 */
public abstract class UiBlockPagerAdapter extends InternalBasePagerAdapter<UiModule> {

    private final UiModuleManager mManager;

    public UiBlockPagerAdapter(UiModuleManager manager) {
        super();
        mManager = manager;
    }

    @NonNull
    @Override
    protected View getViewFromItem(UiModule item, int position) {
        return item.getRootView();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object != currentItem) { // 支持懒加载
            setVisibleToUser(((UiModule) object), position);
        }
        super.setPrimaryItem(container, position, object);
    }

    protected void setVisibleToUser(UiModule block, int pos) {
        block.onVisibleToUser(true);
        if (currentItem != null) {
            currentItem.onVisibleToUser(false);
        }
    }

    /**
     * 请用{@link #createItem(Object)}来代替
     */
    @Deprecated
    @Override
    protected UiModule createItem(ViewPager viewPager, int position) {
        UiModule uiModule = createItem(getItemType(position));
        mManager.add(viewPager, uiModule);
        return uiModule;
    }

    /**
     * 当没办法从缓存中得到item的时候才会调用此方法
     */
    public abstract
    @NonNull
    UiModule createItem(Object type);
}
