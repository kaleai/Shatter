package kale.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.jakewharton.scalpel.ScalpelFrameLayout;

import kale.ui.shatter.PagerShatter;
import kale.ui.shatter.Shatter;
import kale.ui.shatter.adapter.ShatterPagerAdapter;
import kale.ui.viewpager.ZoomOutPageTransformer;

/**
 * @author Kale
 * @date 2017/10/23
 */
public class ViewPagerActivity extends BaseActivity {

    private static final String TAG = "ViewPagerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_activity);

        final ScalpelFrameLayout scalpelFrameLayout = findViewById(R.id.vp_root_v);
        Switch scalpelOpenSw = findViewById(R.id.scalpel_open_sw);
        scalpelOpenSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                scalpelFrameLayout.setLayerInteractionEnabled(isChecked);
            }
        });

        findViewById(R.id.finish_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", "success");
                setResult(321, intent);
                finish();
            }
        });

        final String[] data = new String[]{"英国", "法国", "爱尔兰", "荷兰", "比利时",
                "卢森堡", "摩纳哥", "泽西", "根西", "马恩岛",
                "中欧", "波兰", "瑞士", "列支敦士登", "奥地利", "匈牙利",
                "捷克", "斯洛伐克", "斯洛文尼亚 ", "德国"};

        final ShatterPagerAdapter adapter = new ShatterPagerAdapter(getShatterManager()) {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public Object getItemType(int position) {
                return "Single Type"; // 只有一种类型的Item
            }

            @NonNull
            @Override
            public Shatter createItem(Object type) {
                return new PagerShatter();
            }

            /**
             * 预加载
             */
            @Override
            protected void afterInstantiateItem(Shatter item, int position) {
                super.afterInstantiateItem(item, position);
                PagerShatter shatter = (PagerShatter) item;
                shatter.handleData(data[position]);
            }

            /**
             * 懒加载
             */
            @Override
            protected void setVisibleToUser(Shatter shatter, int pos) {
                super.setVisibleToUser(shatter, pos);
                /*if (shatter.isVisibleToUser()) {
                    ((PagerShatter) shatter).handleData(data[pos]);
                }*/
            }

            @Override
            public void finishUpdate(ViewGroup container) {
                super.finishUpdate(container);
            }
        };

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2); // 设置预加载的页面个数
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + adapter.getCurrentItem() + "cache size: " + adapter.getCache().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter.notifyDataSetChanged();
    }

}
