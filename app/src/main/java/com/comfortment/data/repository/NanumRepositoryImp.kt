package com.comfortment.data.repository

import android.util.Log
import com.comfortment.data.model.nanum.NanumEntity
import com.comfortment.data.model.nanum.NanumMapper
import com.comfortment.data.source.nanum.NanumRemoteSource
import com.comfortment.domain.model.Nanum
import com.comfortment.domain.repository.nanum.NanumRepository
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class NanumRepositoryImp @Inject constructor(
    private val nanumRemoteSource: NanumRemoteSource,
    private val nanumMapper: NanumMapper
) : NanumRepository {

    override fun getNanum(accessToken: String, type: String, expiry: String): Single<List<Nanum>> =
        nanumRemoteSource.getNanum(accessToken, type, expiry).map {
            val list = ArrayList<Nanum>()
            it.data.forEach { data ->
                Log.e("asdf", data.id)
                list.add(nanumMapper.mapToDomain(data))
            }
            list
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

    override fun editNanum(
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
    ): Single<Response<Unit>> =
        nanumRemoteSource.editNanum(
            accessToken,
            apartmentId,
            nanumId,
            type,
            bankAccount,
            bank,
            imagePath,
            price,
            expiry,
            description,
            refer,
            payAt,
            title,
            currentState
        )

    override fun uploadImage(accessToken: String, imageUrl: String): Single<String> =
        nanumRemoteSource.uploadImage(accessToken, imageUrl)!!
            .map {
                //Log.e("asdf", it.string())
                it.string().toString()
            }

    override fun showNanumDetail(accessToken: String, nanumId: String): Single<Nanum> =
        nanumRemoteSource.showNanumDetail(accessToken, nanumId)
            .map {
                nanumMapper.mapToDomain(
                    NanumEntity(
                        it.entity.id,
                        it.entity.apartmentId,
                        it.entity.nanumId,
                        it.entity.roomNumber,
                        it.entity.ownerName,
                        it.entity.phoneNumber,
                        it.entity.type,
                        it.entity.bankAccount,
                        it.entity.bank,
                        it.entity.imagePath,
                        it.entity.price,
                        it.entity.expiry,
                        it.entity.description,
                        it.entity.referTo,
                        it.entity.payAt,
                        it.entity.title,
                        it.entity.currentState
                    )
                )
            }

    override fun starNanum(accessToken: String, apartmentId: String, nanumId: String): Single<Response<Unit>> =
        nanumRemoteSource.starNanum(accessToken, apartmentId, nanumId)

    override fun getStarNanum(accessToken: String, apartmentId: String): Single<List<Nanum>> =
        nanumRemoteSource.getStarNanum(accessToken, apartmentId)
            .map {
                val list = ArrayList<Nanum>()
                it.data.forEach { data ->
                    list.add(nanumMapper.mapToDomain(data))
                }
                list
            }

    override fun getRaisedNanum(accessToken: String, apartmentId: String): Single<List<Nanum>> =
        nanumRemoteSource.getRaisedNanum(accessToken, apartmentId)
            .map {
                val list = ArrayList<Nanum>()
                it.data.forEach { data ->
                    list.add(nanumMapper.mapToDomain(data))
                }
                list
            }

    override fun joinNanum(accessToken: String, apartmentId: String, nanumId: String): Single<Response<Unit>> =
        nanumRemoteSource.joinNanum(accessToken, apartmentId, nanumId)

    override fun getJoinNanum(accessToken: String, apartmentId: String): Single<List<Nanum>> =
        nanumRemoteSource.getJoinNanum(accessToken, apartmentId)
            .map {
                val list = ArrayList<Nanum>()
                it.data.forEach { data ->
                    list.add(nanumMapper.mapToDomain(data))
                }
                list
            }

    override fun setRaisedState(
        accessToken: String,
        apartmentId: String,
        nanumId: String,
        currentState: String
    ): Single<Response<Unit>> = nanumRemoteSource.setRaisedState(accessToken, apartmentId, nanumId, currentState)

}