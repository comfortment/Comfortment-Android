package com.comfortment.data.repository

import com.comfortment.data.source.nanum.NanumRemoteSource
import com.comfortment.domain.model.Nanum
import com.comfortment.domain.repository.nanum.NanumRepository
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class NanumRepositoryImp @Inject constructor(
    private val nanumRemoteSource: NanumRemoteSource
) : NanumRepository {

    override fun getNanum(accessToken: String, type: String, expiry: String): Single<List<Nanum>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postNanum(
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
    ): Single<Response<Unit>> =
        nanumRemoteSource.postNanum(
            accessToken,
            apartmentId,
            type,
            bankAccount,
            bank,
            imagePath,
            price,
            expiry,
            description,
            refer,
            payAt,
            title
        )

    override fun editNanum(accessToken: String, apartmentId: String, nanumId: String): Single<Response<Unit>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNanumDetail(accessToken: String, nanumId: String): Single<Nanum> =
        nanumRemoteSource.showNanumDetail(accessToken, nanumId)

    override fun starNanum(accessToken: String, apartmentId: String, nanumId: String): Single<Response<Unit>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStarNanum(accessToken: String, apartmentId: String): Single<List<Nanum>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRaisedNanum(accessToken: String, apartmentId: String): Single<List<Nanum>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun joinNanum(accessToken: String, apartmentId: String, nanumId: String): Single<Response<Unit>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getJoinNanum(accessToken: String, apartmentId: String): Single<List<Nanum>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRaisedState(accessToken: String, apartmentId: String, nanumId: String): Single<Response<Unit>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}