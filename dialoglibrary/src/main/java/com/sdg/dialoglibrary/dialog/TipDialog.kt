package com.sdg.dialoglibrary.dialog

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.sdg.dialoglibrary.R
import com.sdg.dialoglibrary.utils.SdgUtils

class TipDialog : BaseDialog() {

    private var confirm: String? = null

    private var content: String? = null

    private var callback: Callback? = null

    //返回键取消
    private var backCancel: Boolean = false
    //弹框外部取消
    private var canceledOutside: Boolean = false

    private var width: Float = 5/6f

    override fun getLayoutId(): Int = R.layout.dialog_tip

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_message).apply {
            text = content
        }

        view.findViewById<TextView>(R.id.tv_yes).apply {
            text = confirm
            setOnClickListener {
                dismiss()
                callback?.onConfirm()
            }
        }
    }

    fun setConfirm(confirm: String): TipDialog {
        this.confirm = confirm
        return this
    }

    fun setContent(content: String): TipDialog {
        this.content = content
        return this
    }

    fun setBackCancel(backCancel: Boolean): TipDialog {
        this.backCancel = backCancel
        return this
    }

    fun setOutsideCancel(canceledOutside: Boolean): TipDialog {
        this.canceledOutside = canceledOutside
        return this
    }

    fun setWidth(width:Float): TipDialog {
        if(width > 1){
            this.width = 5/6f
            return this
        }
        this.width = width
        return this
    }

    override fun windowWidth(): Int {
        return (SdgUtils.getScreenWidthPixels() * width).toInt()
    }

    fun setCallBack(callback: Callback): TipDialog {
        this.callback = callback
        return this
    }

    interface Callback {
        fun onConfirm()
    }

}