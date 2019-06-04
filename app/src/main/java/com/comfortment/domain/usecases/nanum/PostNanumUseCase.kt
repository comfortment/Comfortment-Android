package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single
import retrofit2.Response

class PostNanumUseCase(private val nanumRepository: NanumRepository) :
    UseCase<PostNanumUseCase.Params, Single<Response<Unit>>>() {

    override fun createObservable(params: Params): Single<Response<Unit>> =
        nanumRepository.postNanum(
            params.accessToken,
            params.apartmentId,
            params.type,
            params.bankAccount,
            params.bank,
            params.imagePath,
            params.price,
            params.expiry,
            params.description,
            params.refer,
            params.payAt,
            params.title
        )

    override fun onCleared() {}

    class Params(
        val accessToken: String,
        val apartmentId: String,
        val type: String,
        val bankAccount: String,
        val bank: String,
        val imagePath: String,
        val price: String,
        val expiry: Int,
        val description: String,
        val refer: String,
        val payAt: String,
        val title: String
    )
}