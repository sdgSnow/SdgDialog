package com.sdg.dialoglibrary.pop;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.sdg.dialoglibrary.R;


public class TipPop extends BasePop {

    private TextView tv_title;
    private TextView tv_message;
    private TextView tv_yes;
    private Callback callback;
    private String title;
    private String message;
    private String yes = "确定";
    private float width = 9/10f;

    public TipPop(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_tip;
    }

    @Override
    public void initView() {
        tv_title = mView.findViewById(R.id.tv_title);
        tv_message = mView.findViewById(R.id.tv_message);
        tv_yes = mView.findViewById(R.id.tv_yes);

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                callback.yes();
            }
        });
    }

    public TipPop setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public TipPop setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
        return this;
    }

    public TipPop setMessage(String message) {
        this.message = message;
        tv_message.setText(message);
        return this;
    }

    public TipPop setYes(String yes) {
        this.yes = yes;
        tv_yes.setText(yes);
        return this;
    }

    public TipPop setWidth(float width) {
        this.width = width;
        return this;
    }

    public interface Callback {
        void yes();
    }

}
