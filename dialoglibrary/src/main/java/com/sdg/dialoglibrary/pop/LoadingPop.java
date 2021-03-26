package com.sdg.dialoglibrary.pop;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.sdg.dialoglibrary.R;

public class LoadingPop extends BasePop {

    private TextView tv_message;
    private ImageView loading;
    private String message;
    //加载框的宽高（需保持一致，因此放一起）
    private int widthAndHeight = 100;

    public LoadingPop(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_loading;
    }

    @Override
    public void initView() {
        tv_message = mView.findViewById(R.id.tv_message);
        loading = mView.findViewById(R.id.loading);

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
    }

    private static final int LOADING_ANIM = 1;//加载动画

    public void startAnim() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(loading, "rotation", 0f, 360f);
        rotation.setRepeatCount(Animation.INFINITE);
        rotation.setDuration(1000);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.start();
    }

    public LoadingPop setMessage(String message) {
        this.message = message;
        tv_message.setText(message);
        return this;
    }

}
