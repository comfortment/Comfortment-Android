package com.comfortment.presentation.ui.main.nanum.mine

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comfortment.R
import com.comfortment.domain.model.Nanum
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
    private val nanumPresenter: NanumMinePresenter,
    private val nanumView: NanumMineContract.View
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

        if (nanumList[position].imagePath != null) {
            if (nanumList[position].imagePath!!.isNotEmpty()) {
                Glide.with(holder.itemView.context)
                    .asBitmap()
                    .load(nanumList[position].imagePath)
                    .into(holder.itemView.nanum_image)
            }
        }

        holder.itemView.description_tv.text = nanumList[position].description

        holder.itemView.price_tv.text = "${nanumList[position].price}원"
        holder.itemView.expiry_tv.text =
            if (nanumList[position].expiry < 24) "${nanumList[position].expiry}시간"
            else "${nanumList[position].expiry / 24}일"
        holder.itemView.state_tv.text = when (nanumList[position].currentState) {
            "recruiting" -> "모집중"
            "paid" -> "결제 완료"
            "processing" -> "진행중"
            "done" -> "완료"
            else -> ""
        }

        if (!isRaised) {
            holder.itemView.setOnClickListener {
                nanumView.moveShowDetail(nanumList[position].nanumId!!)
            }
        } else {
            holder.itemView.setOnClickListener {
                nanumView.moveEdit(nanumList[position].nanumId!!)
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

            holder.itemView.recruiting_check.setOnClickListener {
                if (holder.itemView.recruiting_check.isChecked) {
                    nanumPresenter.setStateCurrent(
                        position,
                        nanumList[position].nanumId!!,
                        nanumList[position].currentState!!,
                        "recruiting"
                    )
                }
            }
            holder.itemView.paid_check.setOnClickListener {
                if (holder.itemView.paid_check.isChecked) {
                    nanumPresenter.setStateCurrent(
                        position,
                        nanumList[position].nanumId!!,
                        nanumList[position].currentState!!,
                        "paid"
                    )
                }
            }
            holder.itemView.processing_check.setOnClickListener {
                if (holder.itemView.processing_check.isChecked) {
                    nanumPresenter.setStateCurrent(
                        position,
                        nanumList[position].nanumId!!,
                        nanumList[position].currentState!!,
                        "processing"
                    )
                }
            }
            holder.itemView.done_check.setOnClickListener {
                if (holder.itemView.done_check.isChecked) {
                    nanumPresenter.setStateCurrent(
                        position,
                        nanumList[position].nanumId!!,
                        nanumList[position].currentState!!,
                        "done"
                    )
                }
            }
        }

        itemViewList.add(holder.itemView)
    }

    override fun getItemCount(): Int = nanumList.size

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        holder.setIsRecyclable(true)
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.setIsRecyclable(false)
        super.onViewDetachedFromWindow(holder)
    }

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