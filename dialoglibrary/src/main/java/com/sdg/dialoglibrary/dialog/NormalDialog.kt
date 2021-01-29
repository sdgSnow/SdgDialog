package com.sdg.dialoglibrary.dialog

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.sdg.dialoglibrary.R
import com.sdg.dialoglibrary.utils.AppUtils

class NormalDialog : BaseDialog() {

    private var confirm: String? = "确定"

    private var cancle: String? = "取消"

    private var title: String? = null

    private var content: String? = null

    private var callback: Callback? = null

    //返回键取消
    private var backCancel: Boolean = false
    //弹框外部取消
    private var canceledOutside: Boolean = false

    override fun getLayoutId(): Int = R.layout.dialog_normal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.content).apply {
            text = content
        }

        view.findViewById<ImageView>(R.id.close).apply {
            setOnClickListener {
                dismiss()
            }
        }

        view.findViewById<TextView>(R.id.confirm).apply {
            text = confirm
            setOnClickListener {
                dismiss()
                callback?.onConfirm()
            }
        }

        view.findViewById<TextView>(R.id.cancle).apply {
            text = cancle
            setOnClickListener {
                dismiss()
                callback?.onCancle()
            }
        }
    }

    fun setConfirm(confirm: String): NormalDialog {
        this.confirm = confirm
        return this
    }

    fun setCancle(cancle: String): NormalDialog {
        this.cancle = cancle
        return this
    }

    fun setTitle(title: String): NormalDialog {
        this.title = title
        return this
    }

    fun setContent(content: String): NormalDialog {
        this.content = content
        return this
    }

    fun setBackCancel(backCancel: Boolean): NormalDialog {
        this.backCancel = backCancel
        return this
    }

    fun setOutsideCancel(canceledOutside: Boolean): NormalDialog {
        this.canceledOutside = canceledOutside
        return this
    }

    fun setCallBack(callback: Callback): NormalDialog {
        this.callback = callback
        return this
    }

    override fun windowHeight(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun windowWidth(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    interface Callback {
        fun onConfirm()
        fun onCancle()
    }

}
