package kale.ui.uiblock.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/21
 */
public class UIBlockPagerAdapter extends CommonPagerAdapter<UIBlock>{

    private final UIBlockManager mManager;

    public UIBlockPagerAdapter(UIBlockManager manager, List<UIBlock> items) {
        super(items);
        mManager = manager;
    }

    @Override
    public UIBlock onInitItem(ViewGroup container, UIBlock uiBlock, int position) {
        mManager.add(uiBlock);
        container.addView(uiBlock.getRootView());
        return uiBlock;
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
            item.setUserVisibleHint(true);
            if (currentItem != null) {
                currentItem.setUserVisibleHint(false);
            }
            super.setPrimaryItem(container, position, object);
        }
    }

}
