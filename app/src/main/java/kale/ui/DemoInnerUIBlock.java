package kale.ui;

import android.widget.TextView;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class DemoInnerUIBlock extends UIBlock{

    @Override
    public int getRootViewId() {
        return R.id.inner_tv;
    }
    
    @Override
    protected void bindViews() {
        
    }

    @Override
    protected void setViews() {
        ((TextView) getView(R.id.inner_tv)).setText("Inner UI Block :-)");
    }
}
