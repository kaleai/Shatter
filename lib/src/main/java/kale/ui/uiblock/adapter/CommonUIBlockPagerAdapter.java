package kale.ui.uiblock.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/27
 */
public abstract class CommonUIBlockPagerAdapter<T> extends UIBlockPagerAdapter {

    private List<T> mData;

    public CommonUIBlockPagerAdapter(UIBlockManager manager, @NonNull List<T> data) {
        super(manager);
        mData = data;
    }

    @Override
    public UIBlock instantiateItem(ViewGroup container, int position) {
        UIBlock uiBlock = super.instantiateItem(container, position);
        uiBlock.onUpdateViews(mData.get(position), position);
        return uiBlock;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public void setData(@NonNull List<T> data) {
        mData = data;
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public Object getItemType(int position) {
        return getItemType(mData.get(position));
    }
    
    public T getItem(int position) {
        return mData.get(position);
    }

    public abstract Object getItemType(T t);
}
