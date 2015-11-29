package kale.ui.uiblock.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/21
 * 这个类不关心缓存，仅仅做一般的操作。比如得到view，返回view
 */
public abstract class UIBlockPagerAdapter extends BasePagerAdapter<UIBlock> {

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
        return uiBlock.getRootView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        // 这里必须在destroy的最后做。
        // 因为，被manager清除的UIBlock的view会被置空。所以必须要在父容器remove掉这个view后再做view的置空操作
        mManager.remove((UIBlock) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (object != currentItem) {
            // 支持懒加载
            ((UIBlock) object).onVisibleToUser(true);
            if (currentItem != null) {
                currentItem.onVisibleToUser(false);
            }
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * 请用{@link #onCreateItem(Object)}代替
     */
    @Deprecated
    @Override
    public UIBlock onCreateItem(ViewGroup viewGroup, int position) {
        return onCreateItem(getItemType(position));
    }

    /**
     * 当没办法从缓存中得到item的时候才会调用此方法
     */
    public abstract @NonNull UIBlock onCreateItem(Object type);

}
