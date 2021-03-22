package com.sdg.dialoglibrary.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ActivityUtils

/**
 * BaseDialog
 * Created by sdg on 2021/1/28.
 */
abstract class BaseDialog : DialogFragment() {

    private var cancel: Boolean = false
    private var canceledOutside: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int

    /**
     * 设置宽度 默认匹配
     * */
    open fun windowWidth(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    /**
     * 设置高度 默认匹配
     * */
    open fun windowHeight(): Int = WindowManager.LayoutParams.WRAP_CONTENT

    /**
     * 设置透明度 默认-1f
     * 1.0完全不透明，0.0f完全透明，自身不可见
     * */
    open fun dimAmount(): Float = -1f

    override fun onStart() {
        super.onStart()
        dialog.let { dialog ->
            dialog?.setCancelable(cancel)
            dialog?.setCanceledOnTouchOutside(canceledOutside)
            dialog?.window?.let { window ->
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val attr = window.attributes
                attr.width = windowWidth()
                attr.height = windowHeight()
                if (dimAmount() > 0) {
                    attr.dimAmount = dimAmount()
                }
                attr.gravity = Gravity.CENTER
                window.attributes = attr
            }
        }
    }

    fun show(){
        val topActivity = ActivityUtils.getTopActivity();
        if(topActivity is AppCompatActivity){
            if(isAdded){
                topActivity.supportFragmentManager.beginTransaction().remove(this).commit()
            }
            show(topActivity.supportFragmentManager)
        }
    }

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, tag)
    }

//    fun setCancel(cancel:Boolean):Boolean{
//        this.cancel = cancel
//        return cancel
//    }
//
//    fun setOutsideCancel(canceledOutside:Boolean):Boolean{
//        this.canceledOutside = canceledOutside
//        return canceledOutside
//    }

}