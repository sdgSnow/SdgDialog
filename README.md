# SdgPop
dialog
使用方法：
1.allprojects {
  		repositories {
  			...
  			maven { url 'https://jitpack.io' }
  		}
  	}

    请从2.0.4开始
	dependencies {
	        implementation 'com.github.sdgSnow:SdgPop:2.0.4'
	}

3.各弹出框使用：
fun tipPop(view: View) {
        TipPop().setConfirm("确定").setContent("我是一个提示框").setCallBack(object :
            TipPop.Callback{
            override fun onConfirm() {
                Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
            }
        }).show(supportFragmentManager)
    }

    fun loadingPop(view: View) {
        PopManager.get().showLoading(this)
        handler.postDelayed(runnable,2000)
    }

    fun normalPop(view: View) {
        NormalPop().setTitle("普通弹出框").setContent("this is a normal dialog").setCallBack(object : NormalPop.Callback{
            override fun onConfirm() {
                Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
            }

            override fun onCancle() {
                Toast.makeText(this@MainActivity, "取消", Toast.LENGTH_SHORT).show()
            }
        }).show(supportFragmentManager)
    }

    fun progressPop(view: View) {
        PopManager.get().showProgress(this)
        handler.postDelayed(runnableProgress,100)
    }
    
    var pro = 0

    var handler = Handler()
    var runnable: Runnable = object : Runnable {
        override fun run() {
            //要做的事情
            handler.postDelayed(this, 100)
            PopManager.get().hideLoading()
            handler.removeCallbacks(this)
        }
    }

    var runnableProgress: Runnable = object : Runnable {
        override fun run() {
            // TODO Auto-generated method stub
            //要做的事情

            pro++
            PopManager.get().setProgress(pro)
            handler.postDelayed(this, 50)
            if (pro == 100) {
                pro = 0
                handler.removeCallbacks(this)
                PopManager.get().hideProgress()
                Toast.makeText(this@MainActivity, "上传成功", Toast.LENGTH_SHORT).show()
            }
        }
    }
