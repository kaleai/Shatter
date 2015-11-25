package kale.ui.uiblock.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Jack Tony
 * @date 2015/11/21
 * 
 * 适合于item对象不是很多的情况，不适合传入数量太多的item。
 * 如果要更新某个对象，可以通过{@link #getItem(int)}来得到，然后调用其public方法
 * 如果做了item的删减，可以调用{@link #notifyDataSetChanged()}来更新。
 * 它会自动调用{@link #instantiateItem(ViewGroup, int)}重新初始化一次。
 */
public abstract class CommonPagerAdapter<T> extends PagerAdapter {

    private int mChildCount = 0;

    protected List<T> itemList;

    protected T currentItem;

    public CommonPagerAdapter(List<T> items) {
        itemList = items;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // 如果onInitItem()返回的不是view对象，那么请复写这里
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
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentItem = (T) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        onDestroyItem(container, (T) object, position);
    }

    public T getItem(int position) {
        return itemList.get(position);
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public T getCurrentItem() {
        return currentItem;
    }

    public abstract T onInitItem(ViewGroup container, T t, int position);

    public abstract void onDestroyItem(ViewGroup container, T t, int position);

}
