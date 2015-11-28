package kale.ui.uiblock.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/21
 */
public abstract class UIBlockPagerAdapter extends CommonPagerAdapter<UIBlock>{

    private final UIBlockManager mManager;
    
    public UIBlockPagerAdapter(UIBlockManager manager) {
        super();
        mManager = manager;
    }

    @Override
    public View getViewFromItem(UIBlock uiBlock) {
        return uiBlock.getRootView();
    }
    
    @Override
    public View getWillBeAddedView(UIBlock uiBlock, int position) {
        mManager.add(uiBlock);
        if (uiBlock != currentItem) {
            uiBlock.onVisibleToUser(false);
        }
        return uiBlock.getRootView();
    }

    @Override
    public View getWillBeDestroyedView(UIBlock uiBlock, int position) {
        mManager.remove(uiBlock);
        return uiBlock.getRootView();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        UIBlock item = (UIBlock) object;
        if (item != currentItem) {
            // 支持懒加载
            item.onVisibleToUser(true);
            if (currentItem != null) {
                currentItem.onVisibleToUser(false);
            }
            super.setPrimaryItem(container, position, object);
        }
    }

    /**
     * 请用{@link #onCreateItem(Object)}代替
     */
    @Deprecated
    @Override
    public UIBlock onCreateItem(int position) {
        return onCreateItem(getItemType(position));
    }

    /**
     * 当没办法从缓存中得到item的时候才会调用此方法
     */
    public abstract @NonNull UIBlock onCreateItem(Object type);

}
