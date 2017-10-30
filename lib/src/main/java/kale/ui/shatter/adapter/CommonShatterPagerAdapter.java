package kale.ui.shatter.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.annotation.Nullable;

import kale.ui.shatter.Shatter;
import kale.ui.shatter.ShatterManager;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jack Tony
 * @date 2015/11/27
 */
public abstract class CommonShatterPagerAdapter<T> extends ShatterPagerAdapter {

    @Setter @Getter
    private List<T> data;

    public CommonShatterPagerAdapter(ShatterManager manager, @Nullable List<T> data) {
        super(manager);
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
    protected void setVisibleToUser(Shatter block, int position) {
        super.setVisibleToUser(block, position);
        block.handleData(data.get(position), position);
    }

    public Object getItemType(T t) {
        return -1; // default
    }
}
