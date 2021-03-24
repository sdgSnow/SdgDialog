package com.sdg.sdgdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

    }

    public void tipDialog(View view) {
//        new TipDialog().tipDialog(JavaActivity.this).setContent("tipdialog").setCallBack(new TipDialog.CallBack() {
//            @Override
//            public void yes() {
//                ToastUtils.showLong("确定");
//            }
//        });
    }

    public void loadingDialog(View view) {

    }

    public void normalDialog(View view) {

    }

    public void progressDialog(View view) {

    }
}