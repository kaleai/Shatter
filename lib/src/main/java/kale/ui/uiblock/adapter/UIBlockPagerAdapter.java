package kale.ui.uiblock.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.uiblock.UiBlock;
import kale.ui.uiblock.UiBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/21
 * 这个类不关心缓存，仅仅做一般的操作。比如得到view，返回view
 */
public abstract class UIBlockPagerAdapter extends BasePagerAdapter<UiBlock> {

    private final UiBlockManager mManager;

    private boolean mIsLazy = false;

    public UIBlockPagerAdapter(UiBlockManager manager) {
        this(manager, false);
    }

    public UIBlockPagerAdapter(UiBlockManager manager, boolean isLazy) {
        super();
        mManager = manager;
        mIsLazy = isLazy;
    }

    @NonNull
    @Override
    protected View getViewFromItem(UiBlock item, int position) {
        return item.getRootView();
    }

    @Override
    public UiBlock instantiateItem(ViewGroup container, int position) {
        UiBlock block = super.instantiateItem(container, position);
        if (!mIsLazy) {
            setVisibleToUser(block, position);
        }
        return block;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (mIsLazy && object != currentItem) { // 支持懒加载
            setVisibleToUser(((UiBlock) object), position);
        }
        super.setPrimaryItem(container, position, object);
    }

    protected void setVisibleToUser(UiBlock block, int pos) {
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
    protected UiBlock createItem(ViewPager viewPager, int position) {
        UiBlock uiBlock = createItem(getItemType(position));
        mManager.add(viewPager, uiBlock);
        return uiBlock;
    }

    /**
     * 当没办法从缓存中得到item的时候才会调用此方法
     */
    public abstract
    @NonNull
    UiBlock createItem(Object type);
}
