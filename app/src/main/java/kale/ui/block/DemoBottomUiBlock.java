package kale.ui.block;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kale.ui.R;
import kale.ui.uiblock.UiBlock;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class DemoBottomUiBlock extends UiBlock {

    public static final String TAG_BOTTOM = "Bottom";
    
    private EditText mBottomEt;

    private Button mBottomBtn;

    private DemoBottomUiBlock.Callback mCallback;

    @Override
    public String getTag() {
        return TAG_BOTTOM;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.demo_uiblock;
    }

    @Override
    public void bindViews(View rootView) {
        mBottomEt = getView(R.id.bottom_et);
        mBottomBtn = getView(R.id.bottom_btn);
    }

    @Override
    public void setViews() {
        mBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onBottomBtnClick();
            }
        });
    }

    public void onTextChangeCompleted(@NonNull String text) {
        mBottomEt.setText(text);
    }

    public DemoBottomUiBlock setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }

    public static class Callback {

        public void onBottomBtnClick() {
        }

        void onOtherEvent() {
        }
    }
}
