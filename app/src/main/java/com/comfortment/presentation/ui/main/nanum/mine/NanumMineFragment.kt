package com.comfortment.presentation.ui.main.nanum.mine

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfortment.R
import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BaseFragment
import com.comfortment.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_nanum_mine.*
import kotlinx.android.synthetic.main.nanum_raised_item.view.*
import javax.inject.Inject

class NanumMineFragment : BaseFragment(), NanumMineContract.View {

    @Inject
    lateinit var nanumMinePresenter: NanumMinePresenter

    lateinit var joinedNanumAdapter: NanumMineAdapter
    lateinit var raisedNanumAdapter: NanumMineAdapter

    override val layoutId: Int
        get() = R.layout.fragment_nanum_mine

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        joinedNanumAdapter = NanumMineAdapter(false, nanumMinePresenter, this)
        raisedNanumAdapter = NanumMineAdapter(true, nanumMinePresenter, this)

        nanum_list.adapter = joinedNanumAdapter
        nanum_list.layoutManager = LinearLayoutManager(context)

        nanum_switch.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                switch_tv.text = "조인"
                nanumMinePresenter.loadJoinNanum()
            } else {
                switch_tv.text = "업로드"
                nanumMinePresenter.loadRaisedNanum()
            }
        }

        (activity as MainActivity).apply {
            bottom_app_bar.visibility = View.GONE
            fab.hide()
        }

        nanumMinePresenter.takeView(this)
        nanumMinePresenter.loadJoinNanum()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as MainActivity).apply {
            bottom_app_bar.visibility = View.VISIBLE
            fab.show()
        }
    }

    override fun initNanumList(nanumList: List<Nanum>, isStar: Boolean, isRaised: Boolean) {
        if (!isRaised) {
            nanum_list.adapter = joinedNanumAdapter
            nanumList.forEach {
                joinedNanumAdapter.add(it)
            }
        } else {
            nanum_list.adapter = raisedNanumAdapter
            nanumList.forEach {
                raisedNanumAdapter.add(it)
            }
        }
    }

    override fun clearNanumList(isRaised: Boolean) {
        if (!isRaised) joinedNanumAdapter.remove()
        else raisedNanumAdapter.remove()
    }

    override fun setState(position: Int, currentState: String) {
        when (currentState) {
            "recruiting" -> {
                raisedNanumAdapter.itemViewList[position].recruiting_check.isChecked = true
                raisedNanumAdapter.itemViewList[position].paid_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].processing_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].done_check.isChecked = false
            }
            "paid" -> {
                raisedNanumAdapter.itemViewList[position].recruiting_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].paid_check.isChecked = true
                raisedNanumAdapter.itemViewList[position].processing_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].done_check.isChecked = false
            }
            "processing" -> {
                raisedNanumAdapter.itemViewList[position].recruiting_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].paid_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].processing_check.isChecked = true
                raisedNanumAdapter.itemViewList[position].done_check.isChecked = false
            }
            "done" -> {
                raisedNanumAdapter.itemViewList[position].recruiting_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].paid_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].processing_check.isChecked = false
                raisedNanumAdapter.itemViewList[position].done_check.isChecked = true
            }
        }
    }

    override fun moveShowDetail(nanumId: String) {
        NanumMineFragmentDirections.actionNanumMineFragmentToShowNanumDetailFragment(nanumId, true)
    }

    override fun moveEdit(nanumId: String) {
        NanumMineFragmentDirections.actionNanumMineFragmentToNanumWriteFragment(nanumId)
    }

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    override fun showLoading() = loadingDialog.show(fragmentManager, "Loading")

    override fun hideLoading() = loadingDialog.dismiss()
}