package com.comfortment.presentation.ui.start

import android.content.Intent
import android.os.Bundle
import com.comfortment.R
import com.comfortment.presentation.ui.base.BaseActivity
import com.comfortment.presentation.ui.dialog.LoadingDialog
import com.comfortment.presentation.ui.main.MainActivity
import com.facebook.login.LoginManager
import javax.inject.Inject

class StartActivity : BaseActivity(), StartContract.View {

    override val layoutId: Int
        get() = R.layout.activitiy_sign

    @Inject
    lateinit var startPresenter: StartPresenter

    private val loadingDialog = LoadingDialog.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startPresenter.takeView(this)
        startPresenter.findAuth()

        LoginManager.getInstance().registerCallback(startPresenter.callbackManager, startPresenter.facebookCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(startPresenter.callbackManager.onActivityResult(requestCode, resultCode, data)) return
    }

    override fun moveMain() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    override fun showLoading() = loadingDialog.show(supportFragmentManager, "Loading")

    override fun hideLoading() = loadingDialog.dismiss()
}
