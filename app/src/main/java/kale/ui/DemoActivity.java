package kale.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import kale.ui.base.BaseActivity;
import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.adapter.UIBlockPagerAdapter;

public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        final UIBlockPagerAdapter pagerAdapter = new UIBlockPagerAdapter(getUIBlockManager()) {
            @Override
            public int getCount() {
                return 3; // viewpager的页面数
            }

            @NonNull
            @Override
            public UIBlock onCreateItem(Object type) {
                Log.d("ddd", "onCreateItem: 得到新的");
                return new DemoVpUIBlock();
            }
        };

        final ViewPager viewPager = (ViewPager) findViewById(R.id.top_vp);
        viewPager.setAdapter(pagerAdapter);

        getUIBlockManager()
                .add(new DemoTopUIBlock())
                .add(new DemoBottomUIBlock())
                .add(new DemoMiddleUIBlock());

        findViewById(R.id.noty_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerAdapter.notifyDataSetChanged();
            }
        });
    }

    // 被uiblock调用
    public void changeText() {
        // activity调用uiblock
        getUIBlockManager().get(DemoBottomUIBlock.class).onTextChangeCompleted("Text from activity");
    }

}
