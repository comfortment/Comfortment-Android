package com.comfortment.presentation.ui.main.nanum

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comfortment.R
import com.comfortment.domain.model.Nanum
import kotlinx.android.synthetic.main.nanum_item.view.*

class NanumAdapter(
    private val nanumPresenter: NanumPresenter,
    private val nanumView: NanumContract.View
) : RecyclerView.Adapter<NanumAdapter.ViewHolder>() {

    private val nanumList = ArrayList<Nanum>()
    private var isStar = false
    val itemViewList = ArrayList<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nanum_item, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("asdf", "${nanumList[position].title} ${nanumList[position].imagePath}")

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

        if (isStar) {
            holder.itemView.star_btn.setImageResource(R.drawable.ic_star)
        }

        holder.itemView.star_btn.setOnClickListener {
            nanumPresenter.setStar(position, nanumList[position].nanumId!!)
        }

        holder.itemView.setOnClickListener {
            nanumView.moveShowDetail(nanumList[position].nanumId!!)
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

    fun add(nanum: Nanum, isStar: Boolean) {
        this.isStar = isStar
        nanumList.add(nanum)
        notifyDataSetChanged()
    }

    fun add(list: List<Nanum>) {
        nanumList.addAll(list)
        notifyDataSetChanged()
    }

    fun remove() {
        itemViewList.clear()
        nanumList.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}