package kale.ui;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class DemoBottomUIBlock extends UIBlock{

    @Override
    public int getRootViewId() {
        return R.id.bottom_ub;
    }

    private EditText mBottomEt;
    private Button mBottomBtn;
    
    @Override
    protected void bindViews() {
        mBottomEt = getView(R.id.bottom_et);
        mBottomBtn = getView(R.id.bottom_btn);
    }

    @Override
    protected void setViews() {
        mBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity(DemoActivity.class).changeText();
            }
        });
    }

    public void onTextChangeCompleted(@NonNull String text) {
        mBottomEt.setText(text);
    }
}
