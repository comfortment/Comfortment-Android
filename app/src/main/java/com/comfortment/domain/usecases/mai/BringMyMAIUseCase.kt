package com.comfortment.domain.usecases.mai

import com.comfortment.domain.model.MyMAI
import com.comfortment.domain.repository.mai.MyMAIRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class BringMyMAIUseCase(private val myMAIRepository: MyMAIRepository) :
    UseCase<BringMyMAIUseCase.Params, Single<MyMAI>>() {

    override fun createObservable(params: Params): Single<MyMAI> = myMAIRepository.bringAI()

    override fun onCleared() {}

    class Params
}

