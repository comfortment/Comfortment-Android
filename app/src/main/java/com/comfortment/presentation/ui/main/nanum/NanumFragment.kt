package com.comfortment.presentation.ui.main.nanum

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfortment.R
import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_nanum.*
import javax.inject.Inject

class NanumFragment : BaseFragment(), NanumContract.View {

    @Inject
    lateinit var nanumPresenter: NanumPresenter

    private val nanumAdapter = NanumAdapter(false, this)

    override val layoutId: Int
        get() = R.layout.fragment_nanum

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        range_picker.minValue = 1
        range_picker.maxValue = 24

        date_time_picker.maxValue = 1
        date_time_picker.displayedValues = arrayOf("시간", "일")

        date_time_picker.setOnValueChangedListener { _, _, newVal ->
            range_picker.maxValue = if (newVal == 0) 24 else 30
        }

        nanum_list.adapter = nanumAdapter
        nanum_list.layoutManager = LinearLayoutManager(context)

        find_btn.setOnClickListener {
            val type = when (category_spinner.selectedItem) {
                "묶음구매" -> "bundle"
                "공동구매" -> "joint"
                "떨이" -> "rummage_sale"
                "도우미" -> "worker"
                else -> ""
            }

            val expiry =
                if (date_time_picker.value == 0) String.format("01~%02d%s", range_picker.value, "h")
                else String.format("01~%02d%s", range_picker.value, "d")

            nanumPresenter.loadNanumList(type, expiry)
        }

        nanumPresenter.takeView(this)
    }

    override fun moveShowDetail(nanumId: String) =
        findNavController().navigate(NanumFragmentDirections.actionAOSFragmentToShowNanumDetailFragment(nanumId))

    override fun initNanumList(nanum: Nanum) = nanumAdapter.add(nanum)

    override fun clearNanumList() = nanumAdapter.remove()

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    override fun showLoading() = loadingDialog.show(fragmentManager, "Loading")

    override fun hideLoading() = loadingDialog.dismiss()

}