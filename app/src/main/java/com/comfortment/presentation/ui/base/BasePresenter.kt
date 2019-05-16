package com.comfortment.presentation.ui.base

interface BasePresenter<T> {

    fun takeView(view : T)

    fun dropView()

}