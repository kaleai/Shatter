package kale.ui.uiblock.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kale.ui.uiblock.UiBlock;
import kale.ui.uiblock.UiBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/21
 */
public class UiBlockPagerAdapter extends CommonPagerAdapter<UiBlock>{

    private final UiBlockManager mManager;

    public UiBlockPagerAdapter(UiBlockManager manager, List<UiBlock> items) {
        super(items);
        mManager = manager;
    }

    @Override
    public View onInitItem(ViewGroup container, UiBlock uiBlock, int position) {
        mManager.add(uiBlock);
        container.addView(uiBlock.getRootView());
        return uiBlock.getRootView();
    }

    @Override
    public void onDestroyItem(ViewGroup container, UiBlock uiBlock, int position, View view) {
        container.removeView(view);
        mManager.remove(uiBlock);
    }
}
