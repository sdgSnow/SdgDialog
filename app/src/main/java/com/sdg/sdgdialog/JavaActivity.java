package com.sdg.sdgdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.sdg.dialoglibrary.pop.PopManager;
import com.sdg.dialoglibrary.pop.TipPop;

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

    }

    public void loadingDialog(View view) {

    }

    public void normalDialog(View view) {

    }

    public void progressDialog(View view) {

    }

}