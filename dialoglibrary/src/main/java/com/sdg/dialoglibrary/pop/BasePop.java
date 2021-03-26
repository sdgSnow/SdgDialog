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

public abstract class BasePop extends PopupWindow {

    public Context mContext;
    public View mView;

    public BasePop(Context context){
        super(context);
        this.mContext = context;
        LayoutInflater mInflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflate.inflate(getLayout(), null);

        this.setContentView(mView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(false);

        initView();
    }

    /**
     * 加载布局
     * */
    public abstract int getLayout();

    /**
     * 初始化view及数据
     * */
    public abstract void initView();

    /**
     * 显示pop
     * */
    public void show(){
        Activity topActivity = ActivityUtils.getTopActivity();
        if(topActivity instanceof AppCompatActivity) {
            View decorView = topActivity.getWindow().getDecorView();
            if (decorView.isAttachedToWindow()) {
                showAtLocation(topActivity.getWindow().getDecorView(), Gravity.CENTER,0,0);
            }else {
                decorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewAttachedToWindow(View v) {
                        showAtLocation(topActivity.getWindow().getDecorView(), Gravity.CENTER,0,0);
                        v.removeOnAttachStateChangeListener(this);
                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {
                        dismiss();
                        v.removeOnAttachStateChangeListener(this);
                    }
                });
            }

        }
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }
}
