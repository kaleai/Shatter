package kale.ui.uiblock.adapter;

import android.view.ViewGroup;

import java.util.List;

import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/27
 */
public abstract class CommonUIBlockPagerAdapter<T> extends UIBlockPagerAdapter{

    private List<T> mData;

    public CommonUIBlockPagerAdapter(UIBlockManager manager, List<T> data) {
        super(manager);
        mData = data;
    }

    @Override
    public UIBlock instantiateItem(ViewGroup container, int position) {
        UIBlock uiBlock = super.instantiateItem(container, position);
        uiBlock.onUpdateViews(mData.get(position), position);
        return uiBlock;
    }

    public void setData(List<T> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
