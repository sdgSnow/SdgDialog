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
import com.sdg.dialoglibrary.SdgProgressBar;
import com.sdg.dialoglibrary.utils.SdgUtils;

public class ProgressDialog extends Dialog {

    private Context context;

    private CallBack callBack;

    //加载框的宽高（需保持一致，因此放一起）
    private int widthAndHeight = 140;
    private SdgProgressBar progressBar;

    public ProgressDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_progress,null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
//        lp.height = SdgUtils.dip2px(widthAndHeight);
//        lp.width = SdgUtils.dip2px(widthAndHeight);
        win.setAttributes(lp);

        progressBar = view.findViewById(R.id.progressBar);

        initData();

    }

    private void initData(){

    }

    public void setProgress(int progress){
        progressBar.setProgress(progress);
        if(progress >= 100){
            if(callBack != null) {
                callBack.success();
            }
        }
    }

    public ProgressDialog setWidthAndHeight(int widthAndHeight) {
        this.widthAndHeight = widthAndHeight;
        return this;
    }

    public ProgressDialog setCallBack(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack{
        void success();
    }
}
