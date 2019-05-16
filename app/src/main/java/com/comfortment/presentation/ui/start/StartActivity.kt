package com.comfortment.presentation.ui.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.comfortment.R
import com.comfortment.presentation.ui.base.BaseActivity
import com.comfortment.presentation.ui.main.MainActivity
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activitiy_sign.*
import javax.inject.Inject

class StartActivity : BaseActivity(), StartContract.View {

    override val layoutId: Int
        get() = R.layout.activitiy_sign

    @Inject
    lateinit var startPresenter: StartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startPresenter.findAuth()
        LoginManager.getInstance().registerCallback(startPresenter.callbackManager, startPresenter.facebookCallback)
    }

    override fun onResume() {
        super.onResume()
        startPresenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        startPresenter.dropView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        startPresenter.callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showSignButton() {
        btn.visibility = View.VISIBLE
    }

    override fun moveMain() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}
