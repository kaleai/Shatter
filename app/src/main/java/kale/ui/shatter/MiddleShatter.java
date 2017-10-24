package kale.ui.shatter;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import kale.ui.R;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class MiddleShatter extends Shatter {

    @Override
    protected int getLayoutResId() {
        return R.layout.middle_shatter;
    }

    @Override
    public void bindViews(View rootView) {
        getShatterManager().add(R.id.inner_fl, new InnerShatter());
    }

    @Override
    public void setViews() {
    }

    public static class InnerShatter extends Shatter {

        @Override
        protected int getLayoutResId() {
            return android.R.layout.simple_list_item_1;
        }

        @Override
        public void bindViews(View rootView) {
        }

        @Override
        public void setViews() {
            View root = getRootView();
            
            root.setBackgroundResource(R.color.colorPrimary);
            TextView textView = (TextView) findViewById(android.R.id.text1);
            textView.setGravity(Gravity.CENTER);
            textView.setText(R.string.test_text);
        }

    }
}
