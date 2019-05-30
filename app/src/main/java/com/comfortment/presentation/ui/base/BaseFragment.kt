package com.comfortment.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comfortment.presentation.ui.dialog.LoadingDialog
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    lateinit var rootView: View
    abstract val layoutId: Int

    val loadingDialog = LoadingDialog.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(layoutId, container, false)
        return rootView
    }
}