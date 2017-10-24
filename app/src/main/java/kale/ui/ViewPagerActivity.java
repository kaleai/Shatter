package kale.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import kale.ui.model.DataManager;
import kale.ui.shatter.Shatter;
import kale.ui.shatter.adapter.CommonShatterPagerAdapter;
import kale.ui.shatter.adapter.ShatterPagerAdapter;
import kale.ui.viewpager.VpShatter;

/**
 * @author Kale
 * @date 2017/10/23
 */
public class ViewPagerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_activity);
        
        findViewById(R.id.finish_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", "success");
                setResult(321, intent);
                finish();
            }
        });
    }

    private PagerAdapter getAdapter(boolean b) {
        if (b) {
            return new ShatterPagerAdapter(getShatterManager()) {

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
                public Shatter createItem(Object type) {
                    return new VpShatter();
                }
            };
        } else {
            return new CommonShatterPagerAdapter<String>(
                    getShatterManager(), DataManager.getData()) {
                @Override
                public Object getItemType(String s) {
                    return super.getItemType(s);
                }

                @NonNull
                @Override
                public Shatter createItem(Object type) {
                    return new VpShatter();
                }
            };
        }
    }
}
