package com.sdg.dialoglibrary.pop;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sdg.dialoglibrary.R;

public class NormalPop extends BasePop {

    private String title;
    private String message;
    private String yes = "确定";
    private String no = "取消";

    private float width = 9/10f;

    private Callback callback;
    private TextView tv_title;
    private TextView tv_message;
    private TextView tv_no;
    private TextView tv_yes;
    private boolean backDismiss = false;//默认false，点击返回键不消失

    public NormalPop(AppCompatActivity activity) {
        super(activity,true,true);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_normal;
    }

    @Override
    public void init(View view) {
        tv_title = view.findViewById(R.id.tv_title);
        tv_message = view.findViewById(R.id.tv_message);
        tv_no = view.findViewById(R.id.tv_no);
        tv_yes = view.findViewById(R.id.tv_yes);

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                callback.yes();
            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                callback.no();
            }
        });

        setKeyDownDismiss(backDismiss);
    }

    public NormalPop setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
        return this;
    }

    public NormalPop setMessage(String message) {
        this.message = message;
        tv_message.setText(message);
        return this;
    }

    public NormalPop setBackDismiss(boolean backDismiss) {
        this.backDismiss = backDismiss;
        return this;
    }

    public NormalPop setYes(String yes) {
        this.yes = yes;
        tv_yes.setText(yes);
        return this;
    }

    public NormalPop setNo(String no) {
        this.no = no;
        tv_no.setText(no);
        return this;
    }

    public NormalPop setWidth(float width) {
        if(width > 1){
            this.width = 9/10f;
            return this;
        }
        this.width = width;
        return this;
    }

    public NormalPop setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public interface Callback {
        void yes();
        void no();
    }

}
