package com.sdg.dialoglibrary.test;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sdg.dialoglibrary.R;
import com.sdg.dialoglibrary.utils.SdgUtils;

public class LoadingDialog extends Dialog {

    private Context context;

    private String message;

    private TextView tv_message;

    //加载框的宽高（需保持一致，因此放一起）
    private int widthAndHeight = 100;
    private ImageView loading;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_loading,null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = SdgUtils.dip2px(widthAndHeight);
        lp.width = SdgUtils.dip2px(widthAndHeight);
        win.setAttributes(lp);

        tv_message = view.findViewById(R.id.tv_message);
        loading = findViewById(R.id.loading);

        initData();

    }

    private void initData(){
        tv_message.setText(message);
        
        ObjectAnimator rotation = ObjectAnimator.ofFloat(loading, "rotation", 0f, 360f);
        rotation.setRepeatCount(Animation.INFINITE);
        rotation.setDuration(1000);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.start();
    }

    public LoadingDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public LoadingDialog setWidthAndHeight(int widthAndHeight) {
        this.widthAndHeight = widthAndHeight;
        return this;
    }
}
