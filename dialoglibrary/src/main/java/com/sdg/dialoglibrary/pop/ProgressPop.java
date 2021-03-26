package com.sdg.dialoglibrary.pop;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.sdg.dialoglibrary.R;
import com.sdg.dialoglibrary.SdgProgressBar;

public class ProgressPop extends BasePop {

    private CallBack callBack;

    //加载框的宽高（需保持一致，因此放一起）
    private int widthAndHeight = 140;
    private SdgProgressBar progressBar;

    public ProgressPop(Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_progress;
    }

    @Override
    public void initView() {
        progressBar = mView.findViewById(R.id.progressBar);
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

}
