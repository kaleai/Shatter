package kale.ui;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kale.ui.uiblock.UiBlock;

/**
 * @author Jack Tony
 * @date 2015/9/21
 */
public class DemoBottomUiBlock extends UiBlock<DemoActivity> {

    @Override
    public View initRootView(Activity activity) {
        return activity.findViewById(R.id.bottom_ub);
    }
    
    private EditText mBottomEt;

    private Button mBottomBtn;

    @Override
    protected void onAttach(DemoActivity activity) {
        super.onAttach(activity);
    }

    @Override
    protected void onBindViews() {
        mBottomEt = getView(R.id.bottom_et);
        mBottomBtn = getView(R.id.bottom_btn);
    }

    @Override
    protected void onSetViews() {
        mBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().changeText();
            }
        });
    }

    public void onTextChangeCompleted(@NonNull String text) {
        mBottomEt.setText(text);
    }
}
