package kale.ui.uiblock.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Jack Tony
 * @date 2015/11/21
 */
public abstract class CommonPagerAdapter<T> extends PagerAdapter {

    private int mChildCount = 0;

    protected List<T> itemList;

    public CommonPagerAdapter(List<T> items) {
        itemList = items;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return onInitItem(container, itemList.get(position), position);
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        // 开始逐个刷新item
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        onDestroyItem(container, itemList.get(position), position, (View) object);
    }

    public T getItem(int position) {
        return itemList.get(position);
    }

    public List<T> getItemList() {
        return itemList;
    }

    public abstract View onInitItem(ViewGroup container, T t, int position);

    public abstract void onDestroyItem(ViewGroup container, T t, int position, View view);

}
