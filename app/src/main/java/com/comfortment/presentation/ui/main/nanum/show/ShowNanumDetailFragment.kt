package com.comfortment.presentation.ui.main.nanum.show

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.comfortment.R
import com.comfortment.presentation.ui.base.BaseFragment

class ShowNanumDetailFragment: BaseFragment(), ShowNanumDetailContract.View {

    private val nanumId by lazy { arguments!!.getString("nanumId") }

    override val layoutId: Int
        get() = R.layout.fragment_show_nanum_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    override fun showLoading() = loadingDialog.show(fragmentManager, "Loading")

    override fun hideLoading() = loadingDialog.dismiss()

}