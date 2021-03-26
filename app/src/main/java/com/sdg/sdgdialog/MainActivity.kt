package com.sdg.sdgdialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sdg.dialoglibrary.pop.NormalPop
import com.sdg.dialoglibrary.pop.PopManager
import com.sdg.dialoglibrary.pop.ProgressPop
import com.sdg.dialoglibrary.pop.TipPop

class MainActivity : AppCompatActivity() {

    var pro = 0
    var context: Context? = null

    var handler = Handler()
    var runnable: Runnable = object : Runnable {
        override fun run() {
            //要做的事情
            handler.postDelayed(this, 100)
            PopManager.get().hideLoading()
            handler.removeCallbacks(this)
        }
    }

    private var runnableProgress: Runnable = object : Runnable {
        override fun run() {
            pro++
            handler.postDelayed(this, 50)
            PopManager.get().setProgress(pro).setCallBack {
                PopManager.get().hideProgress()
//                handler.removeCallbacks(this@MainActivity.runnableProgress)
                Toast.makeText(this@MainActivity, "上传成功", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.context = this
    }

    fun tipDialog(view: View) {
        TipPop(context).setTitle("tippop").setMessage("this is a tippop")
            .setCallback {
                Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    fun loadingDialog(view: View) {
        PopManager.get().showLoading(context)
        handler.postDelayed(runnable, 2000)
    }

    fun normalDialog(view: View) {
        NormalPop(context).setTitle("NormalPop").setMessage("this is a NormalPop")
            .setCallback(object : NormalPop.Callback {
                override fun yes() {
                    Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
                }

                override fun no() {
                    Toast.makeText(this@MainActivity, "取消", Toast.LENGTH_SHORT).show()
                }
            }).show()
    }

    fun progressDialog(view: View) {
        PopManager.get().showProgress(context)
        handler.postDelayed(runnableProgress, 100)
    }

    fun javaActivity(view: View) {
        val intent = Intent(this@MainActivity, JavaActivity::class.java)
        startActivity(intent)
    }
}