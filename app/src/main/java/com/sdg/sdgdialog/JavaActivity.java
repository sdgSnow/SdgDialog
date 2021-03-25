package com.sdg.sdgdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.sdg.dialoglibrary.test.DialogManager;
import com.sdg.dialoglibrary.test.LoadingDialog;
import com.sdg.dialoglibrary.test.NormalDialog;
import com.sdg.dialoglibrary.test.ProgressDialog;
import com.sdg.dialoglibrary.test.TipDialog;

public class JavaActivity extends AppCompatActivity {

    private Context context;

    private int pro = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        this.context = this;
    }

    public void tipDialog(View view) {
        new TipDialog(context).setTitle("tipdialog").setMessage("tipdialog").setCallback(new TipDialog.Callback() {
            @Override
            public void yes() {
                ToastUtils.showShort("yes");
            }
        }).show();
    }

    public void loadingDialog(View view) {
        DialogManager.get().showLoading(context);
        handler.postDelayed(loadingRunnable,2000);
    }

    public void normalDialog(View view) {
        new NormalDialog(context).setTitle("NormalDialog")
                .setMessage("this is a NormalDialog").setCallback(new NormalDialog.Callback() {
            @Override
            public void yes() {
                ToastUtils.showShort("yes");
            }

            @Override
            public void no() {
                ToastUtils.showShort("no");
            }
        }).show();
    }

    public void progressDialog(View view) {
        DialogManager.get().showProgress(context);
        handler.postDelayed(progressRunnable,100);
    }

    Handler handler = new Handler();
    Runnable loadingRunnable = new Runnable() {
        @Override
        public void run() {
            DialogManager.get().hideLoading();
            handler.postDelayed(this, 100);
            handler.removeCallbacks(this);
        }
    };

    Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            pro++;
            handler.postDelayed(this, 50);
            DialogManager.get().setProgress(pro).setCallBack(new ProgressDialog.CallBack() {
                @Override
                public void success() {
                    pro = 0;
                    DialogManager.get().hideProgress();
                    ToastUtils.showShort("上传成功");
                    handler.removeCallbacks(progressRunnable);
                }
            });

        }
    };
}