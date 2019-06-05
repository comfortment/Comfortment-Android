package com.comfortment.presentation.ui.main.nanum.show

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import com.comfortment.R
import com.comfortment.presentation.ui.base.BaseFragment
import com.comfortment.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_show_nanum_detail.*
import javax.inject.Inject

class ShowNanumDetailFragment : BaseFragment(), ShowNanumDetailContract.View {

    @Inject
    lateinit var showNanumDetailPresenter: ShowNanumDetailPresenter

    private val nanumId by lazy { arguments!!.getString("nanumId") }
    private val isRaised by lazy { arguments!!.getBoolean("isRaised") }

    override val layoutId: Int
        get() = R.layout.fragment_show_nanum_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).apply {
            bottom_app_bar.visibility = View.GONE
            fab.hide()
        }

        if (isRaised) {
            join_btn.isClickable = false
            ViewCompat.setBackgroundTintList(join_btn, ColorStateList.valueOf(Color.argb(255, 208, 203, 202)))
        } else {
            join_btn.setOnClickListener {
                showNanumDetailPresenter.joinNanum(nanumId)
            }
        }

        showNanumDetailPresenter.takeView(this)

        showNanumDetailPresenter.loadNanumDetail(nanumId)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as MainActivity).apply {
            bottom_app_bar.visibility = View.VISIBLE
            fab.show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initDetail(
        title: String,
        price: Int,
        payAt: Boolean,
        expiry: Int,
        description: String?,
        currentState: String,
        roomNumber: Int,
        name: String,
        phoneNumber: String,
        bankAccount: String?
    ) {
        title_tv.text = title
        price_tv.text = "${price}원"
        if (payAt) {
            first_pay_at_tv.setTextColor(Color.argb(255, 255, 87, 34))
            first_pay_at_tv.typeface = Typeface.DEFAULT_BOLD
        } else {
            latter_pay_at_tv.setTextColor(Color.argb(255, 255, 87, 34))
            latter_pay_at_tv.typeface = Typeface.DEFAULT_BOLD
        }
        expiry_tv.text = "${expiry}일"

        description_tv.text = description

        when (currentState) {
            "recruiting" -> {
                recruiting_check.isChecked = true
            }
            "paid" -> {
                recruiting_check.isChecked = true
                paid_check.isChecked = true
            }
            "processing" -> {
                recruiting_check.isChecked = true
                paid_check.isChecked = true
                processing_check.isChecked = true
            }
            "done" -> {
                recruiting_check.isChecked = true
                paid_check.isChecked = true
                processing_check.isChecked = true
                done_check.isChecked = true
            }
        }

        room_number_tv.text = "${roomNumber}호"
        name_tv.text = name
        phone_number_tv.text = phoneNumber
        bank_tv.text = bankAccount
    }

    override fun moveBack() {
        findNavController().navigateUp()
    }

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    override fun showLoading() = loadingDialog.show(fragmentManager, "Loading")

    override fun hideLoading() = loadingDialog.dismiss()

}