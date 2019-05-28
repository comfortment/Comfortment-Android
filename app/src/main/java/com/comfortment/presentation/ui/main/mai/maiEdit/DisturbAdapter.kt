package com.comfortment.presentation.ui.main.mai.maiEdit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfortment.R
import kotlinx.android.synthetic.main.disturb_add.view.*
import kotlinx.android.synthetic.main.disturb_item.view.*

class DisturbAdapter : RecyclerView.Adapter<DisturbAdapter.ViewHolder>() {

    private val list = ArrayList<Unit>()

    init {
        list.add(Unit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            if (list.size == 1) LayoutInflater.from(parent.context).inflate(R.layout.disturb_add, parent, false)
            else LayoutInflater.from(parent.context).inflate(R.layout.disturb_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemView.add_btn != null) {
            holder.itemView.add_btn.setOnClickListener { add() }
        } else {
            holder.itemView.remove_btn.setOnClickListener { remove(position) }
        }
    }

    override fun getItemCount(): Int = list.size

    private fun add() {
        list.add(list.size - 1, Unit)
        notifyDataSetChanged()
    }

    private fun remove(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}