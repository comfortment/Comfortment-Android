package com.comfortment.presentation.ui.main.nanum

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comfortment.R
import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BaseFragment
import com.comfortment.presentation.ui.main.MainActivity
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_nanum.*
import kotlinx.android.synthetic.main.nanum_item.view.*
import javax.inject.Inject

class NanumFragment : BaseFragment(), NanumContract.View {

    @Inject
    lateinit var nanumPresenter: NanumPresenter

    private lateinit var nanumAdapter: NanumAdapter

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

        nanumAdapter = NanumAdapter(nanumPresenter, this)

        nanum_list.setHasFixedSize(true)
        nanum_list.adapter = nanumAdapter
        nanum_list.layoutManager = LinearLayoutManager(context)
        nanum_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    (activity as MainActivity).apply {
                        if (fab.isShown) {
                            bottom_app_bar.visibility = View.INVISIBLE
                            fab.hide()
                        }
                    }
                } else if (dy < 0) {
                    (activity as MainActivity).apply {
                        if (!fab.isShown) {
                            bottom_app_bar.visibility = View.VISIBLE
                            fab.show()
                        }
                    }
                }
            }
        })

        find_btn.setOnClickListener {
            val type = when (category_spinner.selectedItem) {
                "묶음구매" -> "bundle"
                "공동구매" -> "joint"
                "떨이" -> "rummage_sale"
                "도우미" -> "worker"
                else -> ""
            }

            val expiry =
                if (date_time_picker.value == 0) {
                    range_picker.value
                } else {
                    range_picker.value * 24
                }

/*            val expiry =
                if (date_time_picker.value == 0) String.format("01~%02d%c", range_picker.value, 'h')
                else String.format("01~%02d%c", range_picker.value, 'd')*/

            star_switch.isChecked = false

            nanumPresenter.loadNanumList(type, expiry.toString())
        }

        star_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                nanumPresenter.loadStarNanumList()
            }
        }

        nanumPresenter.takeView(this)
    }

    override fun setItemStarState(position: Int) {
        nanumAdapter.itemViewList[position].star_btn.setImageResource(R.drawable.ic_star)
    }

    override fun moveShowDetail(nanumId: String) {
        (activity as MainActivity).navController.navigate(
            R.id.showNanumDetailFragment,
            bundleOf(Pair("nanumId", nanumId), Pair("isJoined", false))
        )
    }

    override fun initNanumList(nanum: Nanum, isStar: Boolean) {
        nanumAdapter.add(nanum, isStar)
    }

    override fun clearNanumList() = nanumAdapter.remove()

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    override fun showLoading() {
        (activity as MainActivity).apply {
            loadingDialog.show(supportFragmentManager, "Loading")
        }
    }

    override fun hideLoading() = (activity as MainActivity).loadingDialog.dismiss()

}