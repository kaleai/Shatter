package kale.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        startActivity(new Intent(MainActivity.this, DemoActivity.class));

        findViewById(R.id.jump_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoActivity.class));
            }
        });
    }

}
