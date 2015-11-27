package kale.ui.uiblock.adapter;

import android.support.v4.util.ArrayMap;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author Jack Tony
 * @date 2015/11/27
 */
class PagerCache<T> {

    private Map<Object,Queue<T>> mCacheMap;

    public PagerCache() {
        mCacheMap = new ArrayMap<>();
    }

    public T getItem(Object key) {
        Queue<T> queue;
        if ((queue = mCacheMap.get(key)) != null) {
            return queue.poll(); // 如果拿不到也会返回null
        } else {
            return null;
        }
    }

    public void putItem(T item, Object key) {
        Queue<T> queue;
        if ((queue = mCacheMap.get(key)) == null) {
            queue = new LinkedList<>();
            mCacheMap.put(key, queue);
        }
        queue.offer(item);
    }
}
