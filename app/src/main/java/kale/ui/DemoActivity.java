package kale.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.squareup.leakcanary.LeakCanary;

import kale.ui.model.DataManager;
import kale.ui.uimodule.BottomUiModule;
import kale.ui.uimodule.MiddleUiModule;
import kale.ui.uimodule.TopUiModule;
import kale.ui.uimodule.VpUiModule;
import kale.ui.uimodule.LifeUiModule;
import kale.ui.uimodule.UiModule;
import kale.ui.uimodule.adapter.CommonUiBlockPagerAdapter;
import kale.ui.uimodule.adapter.UiBlockPagerAdapter;

public class DemoActivity extends BaseActivity {

    private static final String TAG = "DemoActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LeakCanary.install(getApplication());
        
        setContentView(R.layout.demo_activity);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.top_vp);

        final PagerAdapter pagerAdapter = getAdapter(false);
        viewPager.setAdapter(pagerAdapter);

        BottomUiModule block = new BottomUiModule();
        block.setCallback(new BottomUiModule.Callback() {
            @Override
            public void onBottomBtnClick() {
                onTheBottomBtnClick();
            }
        });

        getUiModuleManager()
                .add(R.id.root_sv, new LifeUiModule())
                .add(R.id.bottom_ub, block)
                .add(R.id.top_ub, new TopUiModule())
                .add(R.id.middle_ub, new MiddleUiModule());

        findViewById(R.id.notify_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerAdapter.notifyDataSetChanged();
            }
        });
        //startActivity(new Intent(this, TestActivity.class));
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    /**
     * 被uiblock调用，这里被内部对象调用时的方法一定要是和内部组件调用事件相关的名字。
     * 比如这里是某个内部组件点击按钮后触发的，就一定要叫做onXxxClick，不要起名为具体的activity中的业务名字。
     * 这样的好处就是内部对象不用知道外部对象的具体做的事情，方便复用和封装
     */
    public void onTheBottomBtnClick() {
        // 被UIBlock调用后会执行具体的业务逻辑
        // activity调用UIBlock，这里可以调用具体的业务逻辑，原则上是外部知晓内部，内部不知晓外部。
        BottomUiModule uiBlock = (BottomUiModule) getUiModuleManager()
                .findUiBlockByTag(BottomUiModule.TAG_BOTTOM);
        
        assert uiBlock != null;
        uiBlock.onTextChangeCompleted("Text from activity");
    }

    private PagerAdapter getAdapter(boolean b) {
        if (b) {
            return new UiBlockPagerAdapter(getUiModuleManager()) {

                @Override
                public Object getItemType(int position) {
                    return position; // 这里可以偷懒直接用pos做类型
                }

                @Override
                public int getCount() {
                    return 3; // viewpager的页面数
                }

                @NonNull
                @Override
                public UiModule createItem(Object type) {
                    return new VpUiModule();
                }
            };
        } else {
            return new CommonUiBlockPagerAdapter<String>(
                    getUiModuleManager(), DataManager.getData()) {
                @Override
                public Object getItemType(String s) {
                    return super.getItemType(s);
                }

                @NonNull
                @Override
                public UiModule createItem(Object type) {
                    return new VpUiModule();
                }
            };
        }
    }

}
