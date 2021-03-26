package com.sdg.dialoglibrary.pop;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.sdg.dialoglibrary.R;


public class TipDialog extends BasePopWin {

    private TextView tv_title;
    private TextView tv_message;
    private TextView tv_yes;
    private Callback callback;
    private String title;
    private String message;
    private String yes = "确定";
    private float width = 9/10f;

    public TipDialog(AppCompatActivity context) {
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

        setKeyDownDismiss(false);
    }

    public TipDialog setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public TipDialog setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
        return this;
    }

    public TipDialog setMessage(String message) {
        this.message = message;
        tv_message.setText(message);
        return this;
    }

    public TipDialog setYes(String yes) {
        this.yes = yes;
        tv_yes.setText(yes);
        return this;
    }

    public TipDialog setWidth(float width) {
        this.width = width;
        return this;
    }

    public interface Callback {
        void yes();
    }

}
