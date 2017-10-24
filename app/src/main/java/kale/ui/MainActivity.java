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

    /**
     * 被uiblock调用，这里被内部对象调用时的方法一定要是和内部组件调用事件相关的名字。
     * 比如这里是某个内部组件点击按钮后触发的，就一定要叫做onXxxClick，不要起名为具体的activity中的业务名字。
     * 这样的好处就是内部对象不用知道外部对象的具体做的事情，方便复用和封装
     */
    public void onTheBottomBtnClick() {
        // 被UIBlock调用后会执行具体的业务逻辑
        // activity调用UIBlock，这里可以调用具体的业务逻辑，原则上是外部知晓内部，内部不知晓外部。
        BottomShatter uiBlock = getShatterManager().findShatterByTag(BottomShatter.TAG);

        assert uiBlock != null;
//        uiBlock.onTextChangeCompleted("Text from mActivity");
    }

}
