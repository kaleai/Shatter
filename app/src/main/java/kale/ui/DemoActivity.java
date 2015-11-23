package kale.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import kale.ui.base.BaseActivity;
import kale.ui.uiblock.UIBlock;
import kale.ui.uiblock.adapter.UiBlockPagerAdapter;

public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        ((ViewPager) findViewById(R.id.top_vp))
                .setAdapter(new UiBlockPagerAdapter(getUiBlockManager(), getUIBlocks()));

        getUiBlockManager()
                .add(new DemoTopUIBlock())
                .add(new DemoBottomUIBlock())
                .add(new DemoMiddleUIBlock());
    }

    @NonNull
    private List<UIBlock> getUIBlocks() {
        List<UIBlock> UIBlockList = new ArrayList<>();
        UIBlockList.add(new DemoVpUiBlock());
        UIBlockList.add(new DemoVpUiBlock());
        UIBlockList.add(new DemoVpUiBlock());
        return UIBlockList;
    }

    // 被uiblock调用
    public void changeText() {
        // activity调用uiblock
        getUiBlockManager().get(DemoBottomUIBlock.class).onTextChangeCompleted("Text from activity");
    }
}
