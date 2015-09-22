package kale.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import kale.ui.base.BaseActivity;

public class DemoActivity extends BaseActivity {

    public static final String KEY_STR = "KEY_STR";

    public static Intent newIntent(Context context, String str) {
        return new Intent(context, DemoActivity.class).putExtra(KEY_STR, str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        String str = getIntent().getStringExtra(KEY_STR);
        Log.d("ddd", str);

        getUIBlockManager()
                .add(new DemoTopUIBlock())
                .add(new DemoBottomUIBlock())
                .add(new DemoMiddleUIBlock());
    }

    public void changeText() {
        getUIBlockManager().get(DemoBottomUIBlock.class).onTextChangeCompleted("Text from activity");
    }
}
