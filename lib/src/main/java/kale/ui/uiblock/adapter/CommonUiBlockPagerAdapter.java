package kale.ui.uiblock.adapter;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import kale.ui.uiblock.UiBlock;
import kale.ui.uiblock.UiBlockManager;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jack Tony
 * @date 2015/11/27
 * 如果在viewpager中的UIBlock有数据源，那么可以用这个做处理
 */
public abstract class CommonUiBlockPagerAdapter<T> extends UiBlockPagerAdapter {

    @Setter @Getter
    private List<T> data;

    public CommonUiBlockPagerAdapter(UiBlockManager manager, @Nullable List<T> data) {
        this(manager, false, data);
    }

    public CommonUiBlockPagerAdapter(UiBlockManager manager, boolean isLazy, @Nullable List<T> data) {
        super(manager, isLazy);
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * {@link #getItemType(Object)}
     */
    @Deprecated
    @Override
    public Object getItemType(int position) {
        return getItemType(data.get(position));
    }

    @Override
    protected void setVisibleToUser(UiBlock block, int position) {
        super.setVisibleToUser(block, position);
        block.handleData(data.get(position), position);
    }

    public Object getItemType(T t) {
        return -1; // default
    }
}
