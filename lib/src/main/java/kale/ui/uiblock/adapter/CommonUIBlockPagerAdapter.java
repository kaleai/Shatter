package kale.ui.uiblock.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/27
 * 如果uiblock中要更新数据，可以这么写
 * 目前这里仅仅是做个范例
 */
@Deprecated
abstract class CommonUIBlockPagerAdapter<T> extends UIBlockPagerAdapter2 {

    private List<T> mData;

    public CommonUIBlockPagerAdapter(UIBlockManager manager, @NonNull List<T> data) {
        super(manager);
        mData = data;
    }

    @Override
    protected View getWillBeAddedView(View item, int position) {
        View view = super.getWillBeAddedView(item, position);
        UIBlock uiBlock = (UIBlock) view.getTag();
        // uiBlock.onUpdateViews(mData.get(position), position);
        return view;
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

    public Object getItemType(T t) {
        return -1; // default
    }
}
