package com.sdg.sdgdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

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