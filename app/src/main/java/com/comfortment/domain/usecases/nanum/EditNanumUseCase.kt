package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single
import retrofit2.Response

class EditNanumUseCase(private val nanumRepository: NanumRepository) :
    UseCase<EditNanumUseCase.Params, Single<Response<Unit>>>() {

    override fun createObservable(params: Params): Single<Response<Unit>> =
        nanumRepository.editNanum(
            params.accessToken,
            params.apartmentId,
            params.nanumId,
            params.type,
            params.bankAccount,
            params.bank,
            params.imagePath,
            params.price,
            params.expiry,
            params.description,
            params.refer,
            params.payAt,
            params.title,
            params.currentState
        )

    override fun onCleared() {}

    data class Params(
        val accessToken: String,
        val apartmentId: String,
        val nanumId: String,
        val type: String,
        val bankAccount: String,
        val bank: String,
        val imagePath: String,
        val price: String,
        val expiry: Int,
        val description: String,
        val refer: String,
        val payAt: String,
        val title: String,
        val currentState: String
    )
}