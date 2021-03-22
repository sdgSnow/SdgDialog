package com.sdg.dialoglibrary.dialog

import android.os.Bundle
import android.view.View
import com.sdg.dialoglibrary.R
import com.sdg.dialoglibrary.utils.SdgUtils
import kotlinx.android.synthetic.main.dialog_progress.*

class ProgressDialog : BaseDialog() {

    //返回键取消
    private var backCancel: Boolean = false

    //弹框外部取消
    private var canceledOutside: Boolean = false

    //加载框的宽高（需保持一致，因此放一起）
    private var widthAndHeight:Float = 100f

    override fun getLayoutId(): Int = R.layout.dialog_progress

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setProgress(progress:Int){
        progressBar.setProgress(progress)
    }

    fun setBackCancel(backCancel: Boolean): ProgressDialog {
        this.backCancel = backCancel
        return this
    }

    fun setOutsideCancel(canceledOutside: Boolean): ProgressDialog {
        this.canceledOutside = canceledOutside
        return this
    }

    fun setWidthAndHeight(widthAndHeight:Float):ProgressDialog{
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
