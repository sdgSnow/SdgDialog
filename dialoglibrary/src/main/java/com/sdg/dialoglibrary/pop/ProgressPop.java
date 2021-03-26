package com.sdg.dialoglibrary.pop;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sdg.dialoglibrary.R;
import com.sdg.dialoglibrary.SdgProgressBar;

public class ProgressPop extends BasePop {

    private CallBack callBack;

    //加载框的宽高（需保持一致，因此放一起）
    private int widthAndHeight = 140;
    private SdgProgressBar progressBar;
    private boolean backDismiss = false;//默认false，点击返回键不消失

    public ProgressPop(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_progress;
    }

    @Override
    public void init(View view) {
        progressBar = view.findViewById(R.id.progressBar);

        setKeyDownDismiss(backDismiss);
    }


    public void setProgress(int progress){
        progressBar.setProgress(progress);
        if(progress >= 100){
            if(callBack != null) {
                callBack.success();
            }
        }
    }

    public ProgressPop setWidthAndHeight(int widthAndHeight) {
        this.widthAndHeight = widthAndHeight;
        return this;
    }

    public ProgressPop setCallBack(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack{
        void success();
    }

    public ProgressPop setBackDismiss(boolean backDismiss) {
        this.backDismiss = backDismiss;
        return this;
    }

}
