package com.comfortment.presentation.ui.main.mai.maiEdit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.comfortment.R
import kotlinx.android.synthetic.main.disturb_add.view.*
import kotlinx.android.synthetic.main.disturb_item.view.*

class DisturbAdapter : RecyclerView.Adapter<DisturbAdapter.ViewHolder>() {

    private val maiList = ArrayList<Int>()
    val viewList = ArrayList<ViewHolder?>()

    init {
        maiList.add(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            if (viewType == 0) LayoutInflater.from(parent.context).inflate(R.layout.disturb_add, parent, false)
            else LayoutInflater.from(parent.context).inflate(R.layout.disturb_item, parent, false)
        )

    override fun getItemViewType(position: Int): Int = maiList[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemView.add_btn != null) {
            holder.itemView.add_btn.setOnClickListener { add() }
        } else {
            holder.itemView.remove_btn.setOnClickListener { remove(position) }
            viewList[position] = holder
        }
    }

    override fun getItemCount(): Int = maiList.size

    private fun add() {
        maiList.add(maiList.size - 1, 1)
        viewList.add(null)
        notifyDataSetChanged()
    }

    private fun remove(position: Int) {
        maiList.removeAt(position)
        viewList.removeAt(position)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}