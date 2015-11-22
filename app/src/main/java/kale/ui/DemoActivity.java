package kale.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import kale.ui.base.BaseActivity;
import kale.ui.uiblock.UiBlock;
import kale.ui.uiblock.adapter.UiBlockPagerAdapter;

public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        ((ViewPager) findViewById(R.id.top_vp))
                .setAdapter(new UiBlockPagerAdapter(getUiBlockManager(), getUIBlocks()));

        getUiBlockManager()
                .add(new DemoTopUiBlock())
                .add(new DemoBottomUiBlock())
                .add(new DemoMiddleUiBlock());
    }

    @NonNull
    private List<UiBlock> getUIBlocks() {
        List<UiBlock> uiBlockList = new ArrayList<>();
        uiBlockList.add(new DemoVpUiBlock());
        uiBlockList.add(new DemoVpUiBlock());
        uiBlockList.add(new DemoVpUiBlock());
        return uiBlockList;
    }

    // 被uiblock调用
    public void changeText() {
        // activity调用uiblock
        getUiBlockManager().get(DemoBottomUiBlock.class).onTextChangeCompleted("Text from activity");
    }
}
