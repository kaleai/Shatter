package kale.ui;

import android.os.Bundle;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import kale.ui.shatter.BottomShatter;
import kale.ui.shatter.LifeShatter;
import kale.ui.shatter.MiddleShatter;
import kale.ui.shatter.TopShatter;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeakCanary.install(getApplication());
        setContentView(R.layout.main_activity);

        getShatterManager()
                .add(R.id.root_sv, new LifeShatter())
                .add(R.id.top_ub, new TopShatter())
                .add(R.id.middle_fl, new MiddleShatter())
                .add(R.id.bottom_ub, new BottomShatter());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    public void onTheBottomBtnClick() {
        BottomShatter shatter = getShatterManager().findShatterByTag(BottomShatter.TAG);

        assert shatter != null;
//        shatter.onTextChangeCompleted("Text from mActivity");
    }

}
