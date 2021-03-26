package com.sdg.dialoglibrary.pop;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sdg.dialoglibrary.R;

public class LoadingDialog extends BasePopWin{

    private TextView tv_message;
    private ImageView loading;
    private String message;

    public LoadingDialog(AppCompatActivity activity) {
        super(activity,true,true);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_loading;
    }

    @Override
    public void init(View view) {
        tv_message = view.findViewById(R.id.tv_message);
        loading = view.findViewById(R.id.loading);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case LOADING_ANIM:
                        startAnim();
                        break;
                }
            }
        };

        Message msg = new Message();
        msg.what = LOADING_ANIM;
        handler.sendMessage(msg);

        setKeyDownDismiss(false);
    }

    private static final int LOADING_ANIM = 1;//加载动画

    public void startAnim() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(loading, "rotation", 0f, 360f);
        rotation.setRepeatCount(Animation.INFINITE);
        rotation.setDuration(1000);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.start();
    }

    public LoadingDialog setMessage(String message) {
        this.message = message;
        tv_message.setText(message);
        return this;
    }
}
