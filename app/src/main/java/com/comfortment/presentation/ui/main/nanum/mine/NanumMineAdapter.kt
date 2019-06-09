package com.comfortment.presentation.ui.main.nanum.mine

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comfortment.R
import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BasePresenter
import com.comfortment.presentation.ui.base.BaseView
import kotlinx.android.synthetic.main.nanum_item.view.description_tv
import kotlinx.android.synthetic.main.nanum_item.view.expiry_tv
import kotlinx.android.synthetic.main.nanum_item.view.nanum_image
import kotlinx.android.synthetic.main.nanum_item.view.price_tv
import kotlinx.android.synthetic.main.nanum_item.view.star_btn
import kotlinx.android.synthetic.main.nanum_item.view.state_tv
import kotlinx.android.synthetic.main.nanum_item.view.title_tv
import kotlinx.android.synthetic.main.nanum_raised_item.view.*

class NanumMineAdapter(
    private val isRaised: Boolean,
    private val nanumPresenter: BasePresenter<*>,
    private val nanumView: BaseView<*>
) : RecyclerView.Adapter<NanumMineAdapter.ViewHolder>() {

    private val nanumList = ArrayList<Nanum>()
    val itemViewList = ArrayList<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        if (!isRaised) ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nanum_item, parent, false))
        else ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nanum_raised_item, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.star_btn.visibility = View.INVISIBLE

        holder.itemView.title_tv.text = nanumList[position].title

        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(nanumList[position].imagePath)
            .into(holder.itemView.nanum_image)

        holder.itemView.description_tv.text = nanumList[position].description

        holder.itemView.price_tv.text = "${nanumList[position].price}원"
        holder.itemView.expiry_tv.text = "${nanumList[position].expiry}일"
        holder.itemView.state_tv.text = when (nanumList[position].currentState) {
            "recruiting" -> "모집중"
            "paid" -> "결제 완료"
            "processing" -> "진행중"
            "done" -> "완료"
            else -> ""
        }

        if (!isRaised) {
            holder.itemView.setOnClickListener {
                (nanumView as NanumMineContract.View).moveShowDetail(nanumList[position].nanumId)
            }
        } else {
            holder.itemView.setOnClickListener {
                (nanumView as NanumMineContract.View).moveEdit(nanumList[position].nanumId)
            }

            when (nanumList[position].currentState) {
                "recruiting" -> {
                    holder.itemView.recruiting_check.isChecked = true
                    holder.itemView.paid_check.isChecked = false
                    holder.itemView.processing_check.isChecked = false
                    holder.itemView.done_check.isChecked = false
                }
                "paid" -> {
                    holder.itemView.recruiting_check.isChecked = false
                    holder.itemView.paid_check.isChecked = true
                    holder.itemView.processing_check.isChecked = false
                    holder.itemView.done_check.isChecked = false
                }
                "processing" -> {
                    holder.itemView.recruiting_check.isChecked = false
                    holder.itemView.paid_check.isChecked = false
                    holder.itemView.processing_check.isChecked = true
                    holder.itemView.done_check.isChecked = false
                }
                "done" -> {
                    holder.itemView.recruiting_check.isChecked = false
                    holder.itemView.paid_check.isChecked = false
                    holder.itemView.processing_check.isChecked = false
                    holder.itemView.done_check.isChecked = true
                }
            }

            holder.itemView.recruiting_check.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    (nanumPresenter as NanumMinePresenter).setStateCurrent(
                        position,
                        nanumList[position].nanumId,
                        nanumList[position].currentState,
                        "recruiting"
                    )
                }
            }
            holder.itemView.paid_check.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    (nanumPresenter as NanumMinePresenter).setStateCurrent(
                        position,
                        nanumList[position].nanumId,
                        nanumList[position].currentState,
                        "paid"
                    )
                }
            }
            holder.itemView.processing_check.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    (nanumPresenter as NanumMinePresenter).setStateCurrent(
                        position,
                        nanumList[position].nanumId,
                        nanumList[position].currentState,
                        "processing"
                    )
                }
            }
            holder.itemView.done_check.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    (nanumPresenter as NanumMinePresenter).setStateCurrent(
                        position,
                        nanumList[position].nanumId,
                        nanumList[position].currentState,
                        "done"
                    )
                }
            }
        }

        itemViewList.add(holder.itemView)
    }

    override fun getItemCount(): Int = nanumList.size

    fun add(nanum: Nanum) {
        nanumList.add(nanum)
        notifyDataSetChanged()
    }

    fun add(list: List<Nanum>) {
        nanumList.addAll(list)
        notifyDataSetChanged()
    }

    fun remove() {
        nanumList.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}