package com.comfortment.domain.usecases

abstract class UseCase<in Params, out T> where T : Any {

    abstract fun createObservable(params: Params): T

    abstract fun onCleared()

}