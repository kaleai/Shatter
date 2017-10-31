package kale.ui.shatter.adapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.shatter.R;

/**
 * @author Jack Tony
 * @date 2015/11/21
 *
 * 有item缓存的ViewPagerAdapter
 *
 * 如果调用{@link #notifyDataSetChanged()}来更新，
 * 它会自动调用{@link #instantiateItem(ViewGroup, int)}重新new出需要的item，算是完全初始化一次。
 */
public abstract class RecyclerPagerAdapter<T> extends PagerAdapter {

    T currentItem = null;

    private boolean useCache = true;

    /**
     * 这的cache的最大大小是：type * pageSize
     */
    private final PagerCache<T> mCache;

    public RecyclerPagerAdapter() {
        mCache = new PagerCache<>();
    }

    /**
     * 注意：这里必须是view和view的比较
     */
    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == getViewFromItem((T) obj, 0);
    }

    @Override
    public T instantiateItem(ViewGroup container, int position) {
        Object type = getItemType(position);
        T item = mCache.getItem(type); // get item from type
        if (item == null) {
            item = createItem((ViewPager) container, position);
        }
        // 通过item得到将要被add到viewpager中的view
        View view = getViewFromItem(item, position);
        view.setTag(R.id.item_type, type); // set tag

        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        container.addView(view);
        afterInstantiateItem(item, position);
        return item;
    }

    protected void afterInstantiateItem(T item, int position) {

    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (object != currentItem) {
            // 可能是currentItem不等于null，可能是二者不同
            currentItem = (T) object;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        T item = (T) object;
        // 现在通过item拿到其中的view，然后从ViewPager中remove掉
        View view = getViewFromItem(item, position);
        container.removeView(view);
        Object type = view.getTag(R.id.item_type); // get tag
        mCache.putItem(type, item);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE; // 保证notify时可以更新item的个数
    }

    public Object getItemType(int position) {
        return -1; // default
    }

    public T getCurrentItem() {
        return currentItem;
    }

    public PagerCache<T> getCache() {
        return mCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    /**
     * 这里要实现一个从item拿到view的规则
     *
     * @param item     包含view的item对象
     * @param position item所处的位置
     * @return item中的view对象
     */
    protected abstract
    @NonNull
    View getViewFromItem(T item, int position);

    /**
     * 当缓存中无法得到所需item时才会调用
     *
     * @return 需要放入容器的view
     */
    protected abstract T createItem(ViewPager viewPager, int position);

    ///////////////////////////////////////////////////////////////////////////
    // 缓存类
    ///////////////////////////////////////////////////////////////////////////

    public class PagerCache<Item> {

        private final Map<Object, Queue<Item>> mCacheMap;

        PagerCache() {
            mCacheMap = new HashMap<>();
        }

        /**
         * @param type item type
         * @return cache中的item，如果拿不到就返回null
         */
        Item getItem(Object type) {
            Queue<Item> queue = mCacheMap.get(type);
            return queue != null ? queue.poll() : null;
        }

        /**
         * @param type item's type
         */
        void putItem(Object type, Item item) {
            if (!useCache) {
                return;
            }
            Queue<Item> queue;
            if ((queue = mCacheMap.get(type)) == null) {
                queue = new LinkedList<>();
                mCacheMap.put(type, queue);
            }
            queue.offer(item);
        }

        public int size() {
            return mCacheMap.size();
        }
    }

}
