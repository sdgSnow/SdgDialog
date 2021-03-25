package com.sdg.dialoglibrary.test;

import android.content.Context;

import com.sdg.dialoglibrary.utils.SdgUtils;

public class DialogManager {

    private LoadingDialog loadingDialog;
    private ProgressDialog progressDialog;
    private static DialogManager dialogManager;

    public static DialogManager get() {
        if (dialogManager == null) {
            dialogManager = new DialogManager();
            return dialogManager;
        }
        return dialogManager;
    }

    public void showLoading(Context context){
        if(loadingDialog == null){
            loadingDialog = new LoadingDialog(context);
        }else {
            loadingDialog.dismiss();
        }
        loadingDialog.show();
    }

    public void hideLoading(){
        if(loadingDialog != null){
            loadingDialog.dismiss();
        }
    }

    public void showProgress(Context context){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(context);
        }else {
            progressDialog.dismiss();
        }
        progressDialog.show();
    }

    public DialogManager setProgress(int progress){
        progressDialog.setProgress(progress);
        return this;
    }

    public void setCallBack(ProgressDialog.CallBack callBack){
        progressDialog.setCallBack(callBack);
    }

    public void hideProgress(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
