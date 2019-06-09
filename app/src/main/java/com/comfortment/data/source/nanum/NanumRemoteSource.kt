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

    fun getNanum(accessToken: String, type: String, expiry: String) =
        nanumAPI.getNanum("Bearer $accessToken", type, expiry)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

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

        return nanumAPI.postNanum("Bearer $accessToken", apartmentId, json)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())
    }

    fun showNanumDetail(accessToken: String, nanumId: String) =
        nanumAPI.nanumDetail("Bearer $accessToken", nanumId)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun starNanum(accessToken: String, apartmentId: String, nanumId: String) =
        nanumAPI.setStarNanum("Bearer $accessToken", apartmentId, nanumId)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun getStarNanum(accessToken: String, apartmentId: String) =
        nanumAPI.getStarNanum("Bearer $accessToken", apartmentId)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun getRaisedNanum(accessToken: String, apartmentId: String) =
        nanumAPI.getRaisedNanum("Bearer $accessToken", apartmentId)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun joinNanum(accessToken: String, apartmentId: String, nanumId: String) =
        nanumAPI.joinNanum("Bearer $accessToken", apartmentId, nanumId)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun getJoinNanum(accessToken: String, apartmentId: String) =
        nanumAPI.getJoinNanum("Bearer $accessToken", apartmentId)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun setRaisedState(
        accessToken: String,
        apartmentId: String,
        nanumId: String,
        currentState: String
    ) =
        nanumAPI.setRaisedStateNanum("Bearer $accessToken", apartmentId, nanumId, currentState)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())
}