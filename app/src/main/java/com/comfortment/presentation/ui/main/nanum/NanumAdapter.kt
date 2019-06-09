package com.comfortment.presentation.ui.main.nanum

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
import kotlinx.android.synthetic.main.nanum_item.view.*

class NanumAdapter(
    private val nanumPresenter: BasePresenter<*>,
    private val nanumView: BaseView<*>
) : RecyclerView.Adapter<NanumAdapter.ViewHolder>() {

    private val nanumList = ArrayList<Nanum>()
    var isStar = false
    val itemViewList = ArrayList<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nanum_item, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

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

        if (isStar) {
            holder.itemView.star_btn.setImageResource(R.drawable.ic_star)
            isStar = false
        }

        holder.itemView.star_btn.setOnClickListener {
            (nanumPresenter as NanumPresenter).setStar(position, nanumList[position].nanumId)
        }
        holder.itemView.setOnClickListener {
            (nanumView as NanumContract.View).moveShowDetail(nanumList[position].nanumId)
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