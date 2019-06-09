package com.comfortment.domain.repository.nanum

import com.comfortment.domain.model.Nanum
import io.reactivex.Single
import retrofit2.Response
import java.io.File

interface NanumRepository {

    fun getNanum(accessToken: String, type: String, expiry: String): Single<List<Nanum>>
    fun postNanum(
        accessToken: String,
        apartmentId: String, type: String,
        bankAccount: String,
        bank: String,
        imagePath: String,
        price: String,
        expiry: Int,
        description: String,
        refer: String,
        payAt: String,
        title: String
    ): Single<Response<Unit>>

    fun editNanum(
        accessToken: String,
        apartmentId: String,
        nanumId: String,
        type: String,
        bankAccount: String,
        bank: String,
        imagePath: String,
        price: String,
        expiry: Int,
        description: String,
        refer: String,
        payAt: String,
        title: String,
        currentState: String
    ): Single<Response<Unit>>

    fun showNanumDetail(accessToken: String, nanumId: String): Single<Nanum>
    fun starNanum(accessToken: String, apartmentId: String, nanumId: String): Single<Response<Unit>>
    fun getStarNanum(accessToken: String, apartmentId: String): Single<List<Nanum>>
    fun getRaisedNanum(accessToken: String, apartmentId: String): Single<List<Nanum>>
    fun joinNanum(accessToken: String, apartmentId: String, nanumId: String): Single<Response<Unit>>
    fun getJoinNanum(accessToken: String, apartmentId: String): Single<List<Nanum>>
    fun setRaisedState(
        accessToken: String,
        apartmentId: String,
        nanumId: String,
        currentState: String
    ): Single<Response<Unit>>

    fun uploadImage(accessToken: String, imageUrl: String): Single<String>

}