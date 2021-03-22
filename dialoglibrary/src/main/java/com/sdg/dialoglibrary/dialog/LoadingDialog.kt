package com.sdg.dialoglibrary.dialog

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.sdg.dialoglibrary.R
import com.sdg.dialoglibrary.utils.SdgUtils

class LoadingDialog : BaseDialog() {

    private var content: String? = "加载中..."

    //返回键取消
    private var backCancel: Boolean = false

    //弹框外部取消
    private var canceledOutside: Boolean = false

    //加载框的宽高（需保持一致，因此放一起）
    private var widthAndHeight:Float = 100f

    override fun getLayoutId(): Int = R.layout.dialog_loading

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.content).apply {
            text = content
        }

        view.findViewById<ImageView>(R.id.loading).apply {
            val loading = view.findViewById<View>(R.id.loading)
            ObjectAnimator.ofFloat(loading, "rotation", 0f, 360f).apply {
                interpolator = LinearInterpolator()
                repeatCount = Animation.INFINITE
                duration = 1000
            }.start()
        }
    }

    fun setContent(content: String): LoadingDialog {
        this.content = content
        return this
    }

    fun setBackCancel(backCancel: Boolean): LoadingDialog {
        this.backCancel = backCancel
        return this
    }

    fun setOutsideCancel(canceledOutside: Boolean): LoadingDialog {
        this.canceledOutside = canceledOutside
        return this
    }

    fun setWidthAndHeight(widthAndHeight:Float):LoadingDialog{
        this.widthAndHeight = widthAndHeight
        return this
    }

    override fun windowWidth(): Int {
        return SdgUtils.dip2px(widthAndHeight)
    }

    override fun windowHeight(): Int {
        return SdgUtils.dip2px(widthAndHeight)
    }

}
