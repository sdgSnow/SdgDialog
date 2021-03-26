package com.sdg.dialoglibrary.pop;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sdg.dialoglibrary.R;
import com.sdg.dialoglibrary.SdgProgressBar;

public class ProgressDialog extends BasePopWin {

    private CallBack callBack;

    //加载框的宽高（需保持一致，因此放一起）
    private int widthAndHeight = 140;
    private SdgProgressBar progressBar;

    public ProgressDialog(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_progress;
    }

    @Override
    public void init(View view) {
        progressBar = view.findViewById(R.id.progressBar);

        setKeyDownDismiss(false);
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
