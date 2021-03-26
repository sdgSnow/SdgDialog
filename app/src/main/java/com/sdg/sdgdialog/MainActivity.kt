package com.sdg.sdgdialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sdg.dialoglibrary.pop.*

class MainActivity : AppCompatActivity() {

    var pro = 0
    var context: Context? = null
    var handler = Handler()

    private lateinit var runnableProgress: Runnable
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.context = this

        runnableProgress = Runnable {
            pro++
            handler.postDelayed(runnableProgress, 50)
            PopManager.get().setProgress(pro).setCallBack {
                pro = 0
                PopManager.get().hideProgress()
                handler.removeCallbacks(this@MainActivity.runnableProgress)
                Toast.makeText(this@MainActivity, "上传成功", Toast.LENGTH_SHORT).show()
            }
        }

        runnable = Runnable {
            //要做的事情
            handler.postDelayed(runnable, 100)
            PopManager.get().hideLoading()
            handler.removeCallbacks(runnable)
        }

    }

    fun tipDialog(view: View) {
        TipPop(this).setTitle("tippop").setMessage("this is a tippop")
            .setCallback {
                Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    fun loadingDialog(view: View) {
        PopManager.get().showLoading(this)
        handler.postDelayed(runnable, 2000)
    }

    fun normalDialog(view: View) {
        NormalPop(this).setTitle("NormalPop").setMessage("this is a NormalPop")
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
        PopManager.get().showProgress(this)
        handler.postDelayed(runnableProgress, 100)
    }

    fun javaActivity(view: View) {
        val intent = Intent(this@MainActivity, JavaActivity::class.java)
        startActivity(intent)
    }
}