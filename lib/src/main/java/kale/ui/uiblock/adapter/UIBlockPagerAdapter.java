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
    public UIBlock instantiateItem(ViewGroup container, int position) {
        UIBlock uiBlock = super.instantiateItem(container, position);
        mManager.add(uiBlock);
        container.addView(uiBlock.getRootView());

        if (uiBlock != currentItem) {
            uiBlock.onVisibleToUser(false);
        }
        return uiBlock;
    }

    /**
     * 请用{@link #getUIBlockItem(Object)}代替
     */
    @Override
    public UIBlock getItem(ViewGroup container, int position) {
        return getUIBlockItem(getItemType(position));
    }

    @Override
    public void onDestroyItem(ViewGroup container, UIBlock uiBlock, int position) {
        container.removeView(uiBlock.getRootView());
        mManager.remove(uiBlock);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((UIBlock) object).getRootView() == view;
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

    public abstract @NonNull UIBlock getUIBlockItem(Object type);

}
