package com.comfortment.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comfortment.R
import com.comfortment.data.remote.ServiceFactory
import com.comfortment.domain.model.Auth
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity() {

    private val callbackManager by lazy { CallbackManager.Factory.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitiy_test)

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                if(result?.accessToken != null){
                    ServiceFactory.makeService(false).oauth("facebook ${result.accessToken.token}")
                        .enqueue(object : Callback<Auth>{
                            override fun onFailure(call: Call<Auth>, t: Throwable) {}

                            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                                if(response.body() != null) {
                                    Log.e("Auth", response.body()!!.userId)
                                    Log.e("Auth", response.body()!!.accessToken)
                                    Log.e("Auth", response.body()!!.refreshToken)
                                }
                            }
                        })
                }
                Log.e("success", result?.accessToken?.token)
            }

            override fun onCancel() {
                Log.e("cancel", "bye")
            }

            override fun onError(error: FacebookException?) {
                Log.e("error", error?.message)
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
