package kale.ui.shatter.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.shatter.Shatter;
import kale.ui.shatter.ShatterManager;

/**
 * @author Jack Tony
 * @date 2015/11/21
 */
public abstract class ShatterPagerAdapter extends RecyclerPagerAdapter<Shatter> {

    private final ShatterManager mManager;

    public ShatterPagerAdapter(ShatterManager manager) {
        super();
        mManager = manager;
    }

    @NonNull
    @Override
    protected View getViewFromItem(Shatter item, int position) {
        return item.getRootView();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (object != currentItem) { // 支持懒加载
            setVisibleToUser(((Shatter) object), position);
        }
        super.setPrimaryItem(container, position, object);
    }

    protected void setVisibleToUser(Shatter shatter, int pos) {
        shatter.onVisibleToUser(true);
        if (currentItem != null) {
            currentItem.onVisibleToUser(false);
        }
    }

    /**
     * 请用{@link #createItem(Object)}来代替
     */
    @Deprecated
    @Override
    protected Shatter createItem(ViewPager viewPager, int position) {
        Shatter shatter = createItem(getItemType(position));
        mManager.add(viewPager, shatter);
        return shatter;
    }

    /**
     * 当没办法从缓存中得到item的时候才会调用此方法
     */
    public abstract
    @NonNull
    Shatter createItem(Object type);
}
