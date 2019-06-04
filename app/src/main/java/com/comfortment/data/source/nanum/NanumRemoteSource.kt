package com.comfortment.data.source.nanum

import com.comfortment.data.remote.api.NanumAPI
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class NanumRemoteSource @Inject constructor(
    private val nanumAPI: NanumAPI,
    private val appSchedulerProvider: AppSchedulerProvider
) {

    fun postNanum(
        accessToken: String,
        apartmentId: String,
        type: String,
        bankAccount: String,
        bank: String,
        imagePath: String,
        price: String,
        expiry: Int,
        description: String,
        refer: String,
        payAt: String,
        title: String
    ): Single<Response<Unit>> {
        val json = JsonObject()

        json.addProperty("type", type)
        json.addProperty("bankAccount", bankAccount)
        json.addProperty("bank", bank)
        json.addProperty("imagePath", imagePath)
        json.addProperty("price", price)
        json.addProperty("expiry", expiry)
        json.addProperty("description", description)
        json.addProperty("refer", refer)
        json.addProperty("payAt", payAt)
        json.addProperty("title", title)

        return nanumAPI.postNanum(accessToken, apartmentId, json)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())
    }

    fun showNanumDetail(accessToken: String, nanumId: String) =
        nanumAPI.nanumDetail(accessToken, nanumId)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())
}