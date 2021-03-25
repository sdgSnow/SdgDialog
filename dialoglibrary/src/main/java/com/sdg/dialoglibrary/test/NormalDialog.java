package com.sdg.dialoglibrary.test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sdg.dialoglibrary.R;
import com.sdg.dialoglibrary.utils.SdgUtils;

public class NormalDialog extends Dialog {

    private Context context;

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

    public NormalDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public NormalDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_normal,null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = (int) (SdgUtils.getScreenWidthPixels() * width);
        win.setAttributes(lp);

        tv_title = view.findViewById(R.id.tv_title);
        tv_message = view.findViewById(R.id.tv_message);
        tv_no = view.findViewById(R.id.tv_no);
        tv_yes = view.findViewById(R.id.tv_yes);

        initData();

    }

    private void initData(){
        tv_title.setText(title);
        tv_message.setText(message);
        tv_no.setText(no);
        tv_yes.setText(yes);

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                callback.no();
            }
        });
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                callback.yes();
            }
        });
    }

    public NormalDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public NormalDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public NormalDialog setYes(String yes) {
        this.yes = yes;
        return this;
    }

    public NormalDialog setNo(String no) {
        this.no = no;
        return this;
    }

    public NormalDialog setWidth(float width) {
        if(width > 1){
            this.width = 9/10f;
            return this;
        }
        this.width = width;
        return this;
    }

    public NormalDialog setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public interface Callback {
        void yes();
        void no();
    }
}
