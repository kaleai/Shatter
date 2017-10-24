package kale.ui.shatter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kale.ui.R;
import kale.ui.ViewPagerActivity;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class BottomShatter extends Shatter {

    public static final String TAG = "BottomShatter";

    private Button mBottomBtn;

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.bottom_shatter;
    }

    @Override
    public void bindViews(View rootView) {
        mBottomBtn = findViewById(R.id.bottom_btn);
    }

    @Override
    public void setViews() {
        mBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "kale");
                intent.setClass(getActivity(), ViewPagerActivity.class);
                startActivityForResult(intent, 123);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
    }

}
