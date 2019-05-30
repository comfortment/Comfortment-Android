package com.comfortment.presentation.ui.base

interface BaseView<T> {
    fun showToast(text: String)

    fun showLoading()

    fun hideLoading()
}