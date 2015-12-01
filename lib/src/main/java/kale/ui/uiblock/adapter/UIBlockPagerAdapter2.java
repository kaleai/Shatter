package kale.ui.uiblock.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.UIBlockManager;

/**
 * @author Jack Tony
 * @date 2015/11/30
 * 这个类不关心缓存，仅仅做一般的操作。比如得到view，返回view
 */
@Deprecated
public abstract class UIBlockPagerAdapter2 extends BasePagerAdapter<View>{

    private final UIBlockManager mManager;

    public UIBlockPagerAdapter2(UIBlockManager manager) {
        mManager = manager;
    }

    @Override
    protected View getViewFromItem(View item) {
        return item;
    }

    @Override
    protected View getWillBeAddedView(View item, int position) {
        UIBlock uiBlock = (UIBlock) item.getTag();
        if (currentItem != null && uiBlock != currentItem.getTag()) {
            uiBlock.onVisibleToUser(false);
        }
        return item;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, @NonNull Object object) {
        // 这里的obj就是view对象
        if (!equals(object, currentItem)) { // 支持懒加载
            UIBlock uiBlock = (UIBlock) ((View) object).getTag();
            uiBlock.onVisibleToUser(true);
            if (currentItem != null) {
                ((UIBlock) currentItem.getTag()).onVisibleToUser(false);
            }
        }
        super.setPrimaryItem(container, position, object);
    }

    @Override
    protected View getWillBeDestroyedView(View item, int position) {
        return item;
    }

    @Override
    protected View onCreateItem(ViewGroup container, int position) {
        UIBlock uiBlock = onCreateItem(getItemType(position));
        mManager.add(uiBlock); // 因为有缓存，所以这里不用remove，manager会自己在界面销毁时清空所有UIBlock
        uiBlock.getRootView().setTag(uiBlock);
        return uiBlock.getRootView();
    }

    /**
     * 当没办法从缓存中得到item的时候才会调用此方法
     */
    public abstract @NonNull
    UIBlock onCreateItem(Object type);

    ///////////////////////////////////////////////////////////////////////////
    // 工具方法
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Null-safe equivalent of {@code a.equals(b)}.
     */
    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

}
