# SdgDialog
dialog
使用方法：
1.allprojects {
  		repositories {
  			...
  			maven { url 'https://jitpack.io' }
  		}
  	}
  

	dependencies {
	        implementation 'com.github.sdgSnow:SdgDialog:v1.0.0'
	}


2.初始化AppUtils：AppUtils.install(this)
3.各弹出框使用：
fun tipDialog(view: View) {
        TipDialog().setConfirm("确定").setContent("我是一个提示框").setCallBack(object :
            TipDialog.Callback{
            override fun onConfirm() {
                Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
            }
        }).show(supportFragmentManager)
    }

    fun loadingDialog(view: View) {
        LoadingDialog.get().show(supportFragmentManager)
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
        }).show(supportFragmentManager)
    }

    fun progressDialog(view: View) {
        ProgressDialog.get().show(supportFragmentManager)
        handler.postDelayed(runnableProgress,100)
    }
    
    var pro = 0

    var handler = Handler()
    var runnable: Runnable = object : Runnable {
        override fun run() {
            //要做的事情
            handler.postDelayed(this, 100)
            LoadingDialog.get().dismiss()
            handler.removeCallbacks(this)
        }
    }

    var runnableProgress: Runnable = object : Runnable {
        override fun run() {
            // TODO Auto-generated method stub
            //要做的事情

            pro++
            ProgressDialog.get().setProgress(pro)
            handler.postDelayed(this, 50)
            if (pro == 100) {
                pro = 0
                handler.removeCallbacks(this)
                ProgressDialog.get().dismiss()
                Toast.makeText(this@MainActivity, "上传成功", Toast.LENGTH_SHORT).show()
            }
        }
    }
