package com.sdg.dialoglibrary.pop;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ActivityUtils;
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

    public NormalPop(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_normal;
    }

    @Override
    public void initView() {
        tv_title = mView.findViewById(R.id.tv_title);
        tv_message = mView.findViewById(R.id.tv_message);
        tv_no = mView.findViewById(R.id.tv_no);
        tv_yes = mView.findViewById(R.id.tv_yes);

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
