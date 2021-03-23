package com.sdg.dialoglibrary.dialog

class DialogManager {
    private var mLoadingDialog: LoadingDialog? = null
    private var mProgressDialog: ProgressDialog? = null

    companion object {
        private var sInstance: DialogManager? = null
        fun get(): DialogManager {
            if (sInstance == null) {
                synchronized(DialogManager::class.java) {
                    if (sInstance == null) {
                        sInstance = DialogManager()
                    }
                }
            }
            return sInstance!!
        }
    }

    /**
     * show loading dialog
     */
    fun showLoading() {
        mLoadingDialog?.let {
            mLoadingDialog!!.dismiss()
        } ?: let {
            mLoadingDialog = LoadingDialog()
        }
        mLoadingDialog!!.show()
    }

    /**
     * dismiss loading dialog
     */
    fun hideLoading() {
        mLoadingDialog?.dismiss()
    }

    /**
     * @desc 进度条的dialog使用需要：先show，再set，最后hide
     * */
    fun showProgress(){
        mProgressDialog?.let {
            mProgressDialog!!.dismiss()
        } ?: let {
            mProgressDialog = ProgressDialog()
        }
        mProgressDialog!!.show()
    }

    /**
     * @desc 设置进度条 设置完在添加成功回调
     * @param progress
     * */
    fun setProgress(progress:Int) : DialogManager{
        mProgressDialog?.setProgress(progress)
        return this
    }

    fun setCallBack(callBack: ProgressDialog.ProgressCallBack){
        mProgressDialog?.setCallBack(callBack)
    }

    fun hideProgress(){
        mProgressDialog?.dismiss()
    }


}