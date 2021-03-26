package com.sdg.dialoglibrary.pop;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.sdg.dialoglibrary.R;
import com.sdg.dialoglibrary.utils.ReflexUtils;

import java.lang.ref.WeakReference;

public abstract class BasePop extends PopupWindow {

    protected AppCompatActivity mActivity;
    private WindowManager.LayoutParams mLayoutParams;
    private OnDismissListener mOnDismissListener;
    private OnShowListener mOnShowListener;
    private float mAlpha = 0.5f;
    private View.OnTouchListener mTouchInterceptor;
    private EventWindowManager mEventWindowManager;
    private boolean keyDownDismiss = true;
    private View.OnTouchListener mOnTouchListener;
    private OnDismissInterceptor mOnDismissInterceptor;

    public BasePop(AppCompatActivity activity) {
        this(activity, false, false);
    }

    public BasePop(AppCompatActivity activity, boolean fillW, boolean fillH) {
        this(activity, true, fillW, fillH);
    }

    public BasePop(AppCompatActivity activity, boolean touchable) {
        this(activity, touchable, false, false);
    }

    public BasePop(AppCompatActivity activity, boolean touchable, boolean fillW, boolean fillH) {
        mActivity = activity;
        mLayoutParams = mActivity.getWindow().getAttributes();
        createPopWin(touchable, fillW, fillH);

        proxySystemWindow(this);
    }

    public void setOutsideTouchable(boolean outsideTouchable){
        super.setOutsideTouchable(outsideTouchable);
        setBackgroundDrawable(outsideTouchable ? new BitmapDrawable(mActivity.getResources(), (Bitmap) null) : null);
        setTouchInterceptor((v, event) -> {
            if (!isOutsideTouchable()) {
                View mView = getContentView();
                if (null != mView)
                    mView.dispatchTouchEvent(event);
            }
            return isFocusable() && !isOutsideTouchable();
        });

    }

    public BasePop(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    private void createPopWin(boolean touchable, boolean fillW, boolean fillH) {
        setFocusable(touchable);
        setWidth(fillW ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(fillH ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(touchable);
        setAnimationStyle(R.style.popwin_common_anim_style);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        super.setOnDismissListener(this::OnDismissListener);

        View view = View.inflate(mActivity, getLayoutRes(), null);
        setContentView(view);
        init(view);
    }

    public void OnDismissListener(){
        if (mOnDismissListener != null)mOnDismissListener.onDismiss();
    }

    /**
     * 监听返回键
     * @param dismiss false则点击返回键不消失 true消失
     * */
    public void setKeyDownDismiss(boolean dismiss){
        keyDownDismiss = dismiss;
    }

    public boolean isKeyDownDismiss(){
        return keyDownDismiss;
    }


    public void setAlpha(float alpha){
        mAlpha = alpha;
    }

    public void show(){
        show(Gravity.CENTER);
    }

    public void show(int gravity){
        show(gravity, 0, 0);
    }

    public void show(int gravity, int x, int y){
        if (mActivity.isFinishing()) return;
        showAtLocation(mActivity.getWindow().getDecorView(), gravity, x, y);
        startEnterShadow();
        if (isShowing() && mOnShowListener != null) {
            mOnShowListener.onShow();
        }
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (mActivity.isFinishing()) return;
        startEnterShadow();
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        if (isShowing() && mOnShowListener != null) {
            mOnShowListener.onShow();
        }
    }


    private void startEnterShadow() {
        ValueAnimator animator = ValueAnimator.ofFloat(1, mAlpha);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            mLayoutParams.alpha = (float) animation.getAnimatedValue();
            mActivity.getWindow().setAttributes(mLayoutParams);
        });
        animator.start();
    }

    private void startExitShadow() {
        ValueAnimator animator = ValueAnimator.ofFloat(mAlpha, 1);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(animation -> {
            mLayoutParams.alpha = (float) animation.getAnimatedValue();
            mActivity.getWindow().setAttributes(mLayoutParams);
        });
        animator.start();
    }


    @Override
    public void dismiss() {
        callSuperDismiss();
    }

    private void callSuperDismiss() {
        try {
            super.dismiss();
            startExitShadow();
            clear();
        } catch (Exception e) {
            e.printStackTrace();
            clear();
        }
    }

    private void clear() {
        if (mEventWindowManager != null) {
            mEventWindowManager.clear();
        }
        if (getContentView() != null){
            ViewParent parent = getContentView().getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(getContentView());
            }
        }
    }

    @Override
    public void setTouchInterceptor(View.OnTouchListener touchInterceptor) {
        mTouchInterceptor = touchInterceptor;
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener){
        mOnTouchListener = onTouchListener;
    }
    public void setOnDismissInterceptor(OnDismissInterceptor onDismissInterceptor){
        mOnDismissInterceptor = onDismissInterceptor;
    }

    public interface OnShowListener{
        void onShow();
    }

    public interface OnDismissInterceptor{
        /**
         * 是否拦截dismiss
         * @return true为拦截, false 不拦截
         */
        boolean onDismiss();
    }

    @Override
    public void setOnDismissListener(OnDismissListener onDismissListener){
        mOnDismissListener = onDismissListener;
    }

    public void setOnShowListener(OnShowListener onShowListener){
        mOnShowListener = onShowListener;
    }


    public abstract int getLayoutRes();


    public abstract void init(View view);


    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);

        proxySystemWindow(this);
    }

    private void proxySystemWindow(PopupWindow popupWindow) {
        try {
            WindowManager windowManager = (WindowManager) ReflexUtils.getField(popupWindow, "mWindowManager");
            if (windowManager == null || windowManager.getClass().getName().equals(EventWindowManager.class.getName())) return;
            mEventWindowManager = new EventWindowManager(windowManager);
            ReflexUtils.setField(popupWindow, "mWindowManager", mEventWindowManager);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    class EventWindowManager implements WindowManager{

        private WindowManager mWindowManager;
        private WeakReference<PopupDecorView> mHackPopupDecorView;


        public EventWindowManager(WindowManager windowManager) {
            mWindowManager = windowManager;
        }

        @Override
        public Display getDefaultDisplay() {
            return mWindowManager == null ? null : mWindowManager.getDefaultDisplay();
        }

        @Override
        public void removeViewImmediate(View view) {
            if (mWindowManager == null || view == null) return;
            if (isPopupInnerDecorView(view) && getHackPopupDecorView() != null){
                PopupDecorView hackPopupDecorView = getHackPopupDecorView();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && !hackPopupDecorView.isAttachedToWindow()) {
                    return;
                }
                mWindowManager.removeViewImmediate(hackPopupDecorView);
                mHackPopupDecorView.clear();
                mHackPopupDecorView = null;
                return;
            }
            mWindowManager.removeViewImmediate(view);
        }

        @Override
        public void addView(View view, ViewGroup.LayoutParams params) {
            if (isPopupInnerDecorView(view)){
                PopupDecorView popupDecorView = new PopupDecorView(view.getContext());
                popupDecorView.addView(view, createLayoutParams(params));
                mHackPopupDecorView = new WeakReference<>(popupDecorView);
                mWindowManager.addView(popupDecorView , params);
            }else {
                mWindowManager.addView(view, params);
            }
        }

        private ViewGroup.LayoutParams createLayoutParams(ViewGroup.LayoutParams params){
            ViewGroup.LayoutParams decorViewLayoutParams;
            if (params instanceof LayoutParams) {
                LayoutParams wp = new LayoutParams();
                wp.copyFrom((LayoutParams) params);
                decorViewLayoutParams = wp;
            } else {
                decorViewLayoutParams = new ViewGroup.LayoutParams(params);
            }
            return decorViewLayoutParams;
        }

        @Override
        public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
            if (mWindowManager == null || view == null) return;
            if (isPopupInnerDecorView(view) && getHackPopupDecorView() != null) {
                PopupDecorView hackPopupDecorView = getHackPopupDecorView();
                mWindowManager.updateViewLayout(hackPopupDecorView, createLayoutParams(params));
            } else {
                mWindowManager.updateViewLayout(view, params);
            }
        }

        @Override
        public void removeView(View view) {
            if (mWindowManager == null || view == null) return;
            if (isPopupInnerDecorView(view) && getHackPopupDecorView() != null) {
                PopupDecorView hackPopupDecorView = getHackPopupDecorView();
                mWindowManager.removeView(hackPopupDecorView);
                mHackPopupDecorView.clear();
                mHackPopupDecorView = null;
            } else {
                mWindowManager.removeView(view);
            }
        }

        private boolean isPopupInnerDecorView(View v) {
            if (v == null) return false;
            String className = v.getClass().getName();
            return TextUtils.equals(className, "android.widget.PopupWindow$PopupDecorView");
        }

        private PopupDecorView getHackPopupDecorView() {
            if (mHackPopupDecorView == null) return null;
            return mHackPopupDecorView.get();
        }

        public void clear() {
            try {
                removeViewImmediate(mHackPopupDecorView.get());
                mHackPopupDecorView.clear();
            } catch (Exception e) {
            }
        }

    }


    private class PopupDecorView extends FrameLayout {


        public PopupDecorView(Context context) {
            super(context);
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                if (getKeyDispatcherState() == null) {
                    return super.dispatchKeyEvent(event);
                }

                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                    final KeyEvent.DispatcherState state = getKeyDispatcherState();
                    if (state != null) {
                        state.startTracking(event, this);
                    }
                    return true;
                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    final KeyEvent.DispatcherState state = getKeyDispatcherState();
                    if (state != null && state.isTracking(event) && !event.isCanceled()) {
                        if (mOnDismissInterceptor != null && !mOnDismissInterceptor.onDismiss()){
                            dismiss();
                            return true;
                        }
                        if (keyDownDismiss){
                            dismiss();
                        }
                        return true;
                    }
                }
                return super.dispatchKeyEvent(event);
            } else {
                return super.dispatchKeyEvent(event);
            }
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            if (mTouchInterceptor != null && mTouchInterceptor.onTouch(this, ev)) {
                return true;
            }
            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            final int x = (int) event.getX();
            final int y = (int) event.getY();

            if (mOnTouchListener != null){
                mOnTouchListener.onTouch(this, event);
            }

            if ((event.getAction() == MotionEvent.ACTION_DOWN) && ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight()))) {
                if (mOnDismissInterceptor != null && !mOnDismissInterceptor.onDismiss()){
                    dismiss();
                    return true;
                }
                if (isOutsideTouchable()) {
                    dismiss();
                }
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                if (mOnDismissInterceptor != null && !mOnDismissInterceptor.onDismiss()){
                    dismiss();
                    return true;
                }
                if (isOutsideTouchable()) {
                    dismiss();
                }
                return true;
            } else {
                return super.onTouchEvent(event);
            }
        }
    }



}
