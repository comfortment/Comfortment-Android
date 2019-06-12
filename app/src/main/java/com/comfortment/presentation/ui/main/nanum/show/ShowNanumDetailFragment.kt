package com.comfortment.presentation.ui.main.nanum.show

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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
    private var isJoined: Boolean = false

    override val layoutId: Int
        get() = R.layout.fragment_show_nanum_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.isJoined = arguments!!.getBoolean("isJoined")

        join_btn.text =
            if (isJoined) "참여 취소"
            else "참여하기"

        back_btn.setOnClickListener {
            findNavController().navigateUp()
        }

        join_btn.setOnClickListener {
            showNanumDetailPresenter.joinNanum(nanumId, isJoined)
        }

        showNanumDetailPresenter.takeView(this)

        showNanumDetailPresenter.loadNanumDetail(nanumId)
    }

    override fun initDetail(
        imagePath: String?,
        title: String,
        price: Long,
        payAt: Boolean,
        expiry: Int,
        description: String?,
        currentState: String,
        roomNumber: Int,
        name: String,
        phoneNumber: String,
        bankAccount: String?
    ) {
        Glide.with(context!!).asBitmap().load(imagePath).into(nanum_image)

        title_tv.text = title
        price_tv.text = "${price}원"
        if (payAt) {
            first_pay_at_tv.setTextColor(Color.argb(255, 255, 87, 34))
            first_pay_at_tv.typeface = Typeface.DEFAULT_BOLD
        } else {
            latter_pay_at_tv.setTextColor(Color.argb(255, 255, 87, 34))
            latter_pay_at_tv.typeface = Typeface.DEFAULT_BOLD
        }

        expiry_tv.text =
            if (expiry < 24) "${expiry}시간"
            else "${expiry / 24}일"

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

    override fun setJoinBtnText(isJoined: Boolean) {
        join_btn.text =
            if (!isJoined) {
                "참여취소"
            } else "참여하기"

        this.isJoined = !isJoined
    }


    override fun moveBack() {
        findNavController().navigateUp()
    }

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    override fun showLoading() {
        (activity as MainActivity).apply {
            loadingDialog.show(supportFragmentManager, "Loading")
        }
    }

    override fun hideLoading() = (activity as MainActivity).loadingDialog.dismiss()

}