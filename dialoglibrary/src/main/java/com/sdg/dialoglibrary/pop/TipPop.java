package com.sdg.dialoglibrary.pop;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    private boolean backDismiss = false;//默认false，点击返回键不消失

    public TipPop(AppCompatActivity context) {
        super(context,true,true);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_tip;
    }

    @Override
    public void init(View view) {
        tv_title = view.findViewById(R.id.tv_title);
        tv_message = view.findViewById(R.id.tv_message);
        tv_yes = view.findViewById(R.id.tv_yes);

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                callback.yes();
            }
        });

        setKeyDownDismiss(backDismiss);
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

    public TipPop setBackDismiss(boolean backDismiss) {
        this.backDismiss = backDismiss;
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
