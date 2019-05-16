package com.comfortment.presentation.ui.start

import android.util.Log
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.presentation.di.qualifier.PerActivity
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerActivity
class StartPresenter @Inject constructor(
    private val authUseCase: AuthUseCase,
    private var appSchedulerProvider: AppSchedulerProvider
) : StartContract.Presenter {

    private var startView: StartContract.View? = null

    private val compositeDisposable = CompositeDisposable()

    val callbackManager: CallbackManager by lazy { CallbackManager.Factory.create() }
    val facebookCallback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) {
            if (result?.accessToken != null) {
                Log.e("success", result.accessToken?.token)
                requestAuth(result.accessToken.token)
            }
        }

        override fun onCancel() {}

        override fun onError(error: FacebookException?) {}
    }

    override fun findAuth() {
        compositeDisposable.add(
            authUseCase.createObservable()
                .subscribeOn(appSchedulerProvider.io())
                .observeOn(appSchedulerProvider.ui())
                .subscribe({ startView?.moveMain() }, { startView?.showSignButton() })
        )
    }

    override fun requestAuth(token: String) {
        compositeDisposable.add(
            authUseCase.createObservable(AuthUseCase.Params("facebook $token"))
                .subscribeOn(appSchedulerProvider.io())
                .observeOn(appSchedulerProvider.ui())
                .subscribe({ startView?.moveMain() }, { Log.e("asdf", "ㄴㄴ.....;;;") })
        )
    }

    override fun takeView(view: StartContract.View) {
        this.startView = view
    }

    override fun dropView() {
        startView = null
    }
}