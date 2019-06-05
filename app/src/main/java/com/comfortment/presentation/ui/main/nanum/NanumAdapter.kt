package com.comfortment.presentation.ui.main.nanum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfortment.R
import com.comfortment.domain.model.Nanum

class NanumAdapter(
    private val isRaised: Boolean,
    private val nanumView: NanumContract.View
) : RecyclerView.Adapter<NanumAdapter.ViewHolder>() {

    private val nanumList = ArrayList<Nanum>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        if (!isRaised) ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nanum_item, parent, false))
        else ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nanum_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            nanumView.moveShowDetail(nanumList[position].nanumId, isRaised)
        }
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