package kale.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import kale.ui.block.DemoBottomUiBlock;
import kale.ui.block.DemoMiddleUiBlock;
import kale.ui.block.DemoTopUiBlock;
import kale.ui.block.DemoVpUiBlock;
import kale.ui.uiblock.UiBlock;
import kale.ui.uiblock.adapter.CommonUiBlockPagerAdapter;
import kale.ui.uiblock.adapter.UIBlockPagerAdapter;
import kale.ui.uiblock.support.AppCompatUiBlockActivity;

public class DemoActivity extends AppCompatUiBlockActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.top_vp);

        final PagerAdapter pagerAdapter = getAdapter(false);
        viewPager.setAdapter(pagerAdapter);

        DemoBottomUiBlock block = new DemoBottomUiBlock();
        block.setCallback(new DemoBottomUiBlock.Callback() {
            @Override
            public void onBottomBtnClick() {
                onTheBottomBtnClick();
            }
        });

        getUiBlockManager()
                .add(R.id.bottom_ub, block)
                .add(R.id.top_ub, new DemoTopUiBlock())
                .add(R.id.middle_ub, new DemoMiddleUiBlock());

        findViewById(R.id.noty_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 被uiblock调用，这里被内部对象调用时的方法一定要是和内部组件调用事件相关的名字。
     * 比如这里是某个内部组件点击按钮后触发的，就一定要叫做onXxxClick，不要起名为具体的activity中的业务名字。
     * 这样的好处就是内部对象不用知道外部对象的具体做的事情，方便复用和封装
     */
    public void onTheBottomBtnClick() {
        // 被UIBlock调用后会执行具体的业务逻辑
        // activity调用UIBlock，这里可以调用具体的业务逻辑，原则上是外部知晓内部，内部不知晓外部。
        DemoBottomUiBlock uiBlock = (DemoBottomUiBlock) getUiBlockManager().get(DemoBottomUiBlock.TAG_BOTTOM);
        assert uiBlock != null;
        uiBlock.onTextChangeCompleted("Text from activity");
    }

    private PagerAdapter getAdapter(boolean b) {
        if (b) {
            return new UIBlockPagerAdapter(getUiBlockManager()) {

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
                public UiBlock createItem(Object type) {
                    Log.d("ddd", "createItem: 得到新的");
                    return new DemoVpUiBlock();
                }
            };
        } else {
            return new CommonUiBlockPagerAdapter<String>(
                    getUiBlockManager(), DataManager.getData()) {
                @Override
                public Object getItemType(String s) {
                    return super.getItemType(s);
                }

                @NonNull
                @Override
                public UiBlock createItem(Object type) {
                    Log.d("ddd", "createItem: 得到新的");
                    return new DemoVpUiBlock();
                }
            };
        }
    }

}
