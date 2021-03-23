package com.sdg.sdgdialog;

import android.os.Handler;

import com.sdg.dialoglibrary.dialog.DialogManager;
import com.sdg.dialoglibrary.dialog.ProgressDialog;

public class JavaTest {

    int pro = 0;

    public void test(){
        DialogManager.Companion.get().showProgress();
        handler.postDelayed(runnable,100);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            pro++;
            handler.postDelayed(runnable,50);
            DialogManager.Companion.get().setProgress(pro).setCallBack(new ProgressDialog.ProgressCallBack() {
                @Override
                public void success() {
                    DialogManager.Companion.get().hideProgress();
                    handler.removeCallbacks(runnable);
                }
            });
        }
    };

    Handler handler = new Handler();
}
