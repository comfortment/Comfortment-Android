package com.comfortment.presentation.ui.start

import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.presentation.di.qualifier.PerActivity
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import javax.inject.Inject

@PerActivity
class StartPresenter @Inject constructor(
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) : StartContract.Presenter, BasePresenter<StartContract.View>(authUseCase, appSchedulerProvider) {

    private var startView: StartContract.View? = null

    val callbackManager: CallbackManager by lazy { CallbackManager.Factory.create() }
    val facebookCallback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) {
            if (result?.accessToken != null) {
                startView?.showLoading()
                requestAuth(result.accessToken.token)
            }
        }

        override fun onCancel() {}

        override fun onError(error: FacebookException?) {}
    }

    override fun findAuth() {
        if (auth.userId.isEmpty()) startView?.showSignButton()
        else startView?.moveMain()
    }

    override fun requestAuth(token: String) {
        compositeDisposable.add(
            authUseCase.createObservable(AuthUseCase.Params("facebook $token", false))
                .subscribeOn(appSchedulerProvider.io())
                .observeOn(appSchedulerProvider.ui())
                .subscribe({ startView?.moveMain() }, { startView?.hideLoading() })
        )
    }

    override fun takeView(view: StartContract.View) {
        this.startView = view
    }

    override fun dropView() {
        startView = null
    }
}