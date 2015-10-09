package kale.ui;

import android.os.Bundle;

import kale.ui.base.BaseActivity;

public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        getUIBlockManager()
                .add(new DemoTopUIBlock())
                .add(new DemoBottomUIBlock())
                .add(new DemoMiddleUIBlock());
    }

    // 被uiblock调用
    public void changeText() {
        // activity调用uiblock
        getUIBlockManager().get(DemoBottomUIBlock.class).onTextChangeCompleted("Text from activity");
    }
}
