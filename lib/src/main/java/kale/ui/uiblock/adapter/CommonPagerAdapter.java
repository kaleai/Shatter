package kale.ui.uiblock.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Jack Tony
 * @date 2015/11/21
 *
 * 如果调用{@link #notifyDataSetChanged()}来更新，
 * 它会自动调用{@link #instantiateItem(ViewGroup, int)}重新new出需要的item，算是完全初始化一次。
 */
abstract class CommonPagerAdapter<T> extends PagerAdapter {

    private int mChildCount = 0;

    protected T currentItem;

    /**
     * 这的cache的最大大小是：type x pageSize
     */
    private final PagerCache<T> mCache;
    
    public CommonPagerAdapter() {
        mCache = new PagerCache<>();
    }

    /**
     * 如果{@link #getItem(ViewGroup, int)}返回的不是view对象，那么请复写这里
     * 注意：这里必须是view和view的比较
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public T instantiateItem(ViewGroup container, int position) {
        T item = mCache.getItem(getItemType(position));
        if (item == null) {
            item = getItem(container, position);
        }
        return item;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentItem = (T) object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        T item = (T) object;
        Object type = getItemType(position);
        onDestroyItem(container, item, position);
        mCache.putItem(item, type);
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

    public T getCurrentItem() {
        return currentItem;
    }

    public Object getItemType(int position){
        return -1;
    }

    /**
     * 仅仅在没有缓存的情况下才会触发
     */
    public abstract T getItem(ViewGroup container, int position);

    public abstract void onDestroyItem(ViewGroup container, T t, int position);
    
}
