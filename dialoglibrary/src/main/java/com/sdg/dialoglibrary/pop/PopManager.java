package com.sdg.dialoglibrary.pop;

import android.content.Context;

public class PopManager {

    private LoadingPop loadingPop;
    private ProgressPop progressPop;
    private static PopManager popManager;

    public static PopManager get() {
        if (popManager == null) {
            popManager = new PopManager();
            return popManager;
        }
        return popManager;
    }

    public void showLoading(Context context){
        if(loadingPop == null){
            loadingPop = new LoadingPop(context);
        }else {
            loadingPop.dismiss();
        }
        loadingPop.show();
    }

    public void hideLoading(){
        if(loadingPop != null){
            loadingPop.dismiss();
            loadingPop = null;
        }
    }

    public void showProgress(Context context){
        if(progressPop == null){
            progressPop = new ProgressPop(context);
        }else {
            progressPop.dismiss();
        }
        progressPop.show();
    }

    public PopManager setProgress(int progress){
        progressPop.setProgress(progress);
        return this;
    }

    public void setCallBack(ProgressPop.CallBack callBack){
        progressPop.setCallBack(callBack);
    }

    public void hideProgress(){
        if(progressPop != null){
            progressPop.dismiss();
        }
    }
}
