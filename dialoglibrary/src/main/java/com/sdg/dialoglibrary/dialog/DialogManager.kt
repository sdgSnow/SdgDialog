package com.sdg.dialoglibrary.dialog

class DialogManager {
    private var mLoadingDialog: LoadingDialog? = null

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
    fun stopLoading() {
        mLoadingDialog?.dismiss()
    }


}