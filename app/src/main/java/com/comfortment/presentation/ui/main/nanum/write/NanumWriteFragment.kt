package com.comfortment.presentation.ui.main.nanum.write

import android.os.Bundle
import android.view.View
import com.comfortment.R
import com.comfortment.presentation.ui.base.BaseFragment
import com.comfortment.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class NanumWriteFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_nanum_write

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).apply {
            bottom_app_bar.visibility = View.GONE
            fab.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as MainActivity).apply {
            bottom_app_bar.visibility = View.VISIBLE
            fab.show()
        }
    }

}