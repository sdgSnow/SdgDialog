package com.sdg.sdgdialog

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sdg.dialoglibrary.dialog.DialogManager
import com.sdg.dialoglibrary.dialog.DialogManager.Companion.get
import com.sdg.dialoglibrary.dialog.NormalDialog
import com.sdg.dialoglibrary.dialog.ProgressDialog.ProgressCallBack
import com.sdg.dialoglibrary.dialog.TipDialog

class MainActivity : AppCompatActivity() {

    var pro = 0

    var handler = Handler()
    var runnable: Runnable = object : Runnable {
        override fun run() {
            //要做的事情
            handler.postDelayed(this, 100)
            DialogManager.get().hideLoading()
            handler.removeCallbacks(this)
        }
    }

    var runnableProgress: Runnable = object : Runnable {
        override fun run() {
            pro++
            handler.postDelayed(this, 50)
            get().setProgress(pro).setCallBack(object : ProgressCallBack {
                override fun success() {
                    get().hideProgress()
//                    handler.removeCallbacks(runnableProgress)
                    Toast.makeText(this@MainActivity, "上传成功", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun tipDialog(view: View) {
        TipDialog().setConfirm("确定").setContent("我是一个提示框").setCallBack(object :
            TipDialog.Callback{
            override fun onConfirm() {
                Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
            }
        }).show()
    }

    fun loadingDialog(view: View) {
        DialogManager.get().showLoading()
        handler.postDelayed(runnable,2000)
    }

    fun normalDialog(view: View) {
        NormalDialog().setTitle("普通弹出框").setContent("this is a normal dialog").setCallBack(object : NormalDialog.Callback{
            override fun onConfirm() {
                Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
            }

            override fun onCancle() {
                Toast.makeText(this@MainActivity, "取消", Toast.LENGTH_SHORT).show()
            }
        }).show()
    }

    fun progressDialog(view: View) {
        JavaTest().test()
//        DialogManager.get().showProgress()
//        handler.postDelayed(runnableProgress,100)
    }

    fun javaActivity(view: View) {
        val intent = Intent(this@MainActivity, JavaActivity::class.java)
        startActivity(intent)
    }
}