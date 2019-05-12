package com.comfortment.presentation.ui.mai.maiPager

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.comfortment.R
import com.comfortment.domain.model.MAI

class MAICardPagerAdapter : PagerAdapter(), CardAdapter {

    private val maiList = ArrayList<MAI?>()
    private val cards = ArrayList<CardView?>()
    private var baseElevation: Float = 0.0f

    fun add(item: MAI?) {
        cards.add(null)
        maiList.add(null)

        Log.e("asdf", cards.size.toString())
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = maiList.size

    override fun getBaseElevation(): Float = baseElevation

    override fun getCardViewAt(position: Int): CardView? = cards[position]

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.card_mai, container, false)

        container.addView(view)
        val cardView = view.findViewById<CardView>(R.id.mai_card)
        bind(maiList[position], cardView)

        if(baseElevation == 0.0f) {
            baseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = baseElevation * MAX_ELEVATION_FACTOR
        cards[position] = cardView

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        cards[position] = null
    }

    private fun bind(item: MAI?, view: View) {

    }
}