package com.comfortment.presentation.ui.main.mai.maiPager

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.comfortment.R
import com.comfortment.domain.model.MAI
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.card_mai.view.*

class MAICardPagerAdapter : PagerAdapter(), CardAdapter {

    private val maiList = ArrayList<MAI?>()
    private val cards = ArrayList<CardView?>()
    private var baseElevation: Float = 0.0f

    fun add(item: MAI?) {
        cards.add(null)
        maiList.add(item)
    }

    fun removeAll() {
        cards.clear()
        maiList.clear()
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

        if (baseElevation == 0.0f)
            baseElevation = cardView.cardElevation

        cardView.maxCardElevation = baseElevation * MAX_ELEVATION_FACTOR
        cards[position] = cardView

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        cards[position] = null
    }

    @SuppressLint("UseSparseArrays", "SetTextI18n")
    private fun bind(item: MAI?, view: View) {
        if (item != null) {
            val values = ArrayList<PieEntry>()
            var distrub = ArrayList<Int>()

            item.disturbTimeRange.forEach {
                for (i in it[0] until it[1]) {
                    distrub.add(i)
                }
            }

            distrub.sort()
            distrub = ArrayList(distrub.toSet())

            val hash = HashMap<Int, Int>()
            for (i in 0 until 23) {
                hash[i] = 0xFFFFFFFF.toInt()
            }

            for (i in 0 until 23) {
                distrub.forEach {
                    if (i == it - 1)
                        hash[i] = Color.argb(255, 214, 151, 135)
                }
                values.add(PieEntry(4f, i))
            }

            val dataSet = PieDataSet(values, "")
            dataSet.colors = hash.values.toList()
            dataSet.setDrawValues(false)
            dataSet.form = Legend.LegendForm.EMPTY

            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            view.distrub_chart.setTouchEnabled(false)
            view.distrub_chart.description = Description().apply { isEnabled = false }
            view.distrub_chart.transparentCircleRadius = 30f
            view.distrub_chart.holeRadius = 15f
            view.distrub_chart.data = data

            view.distrub_chart.animateXY(800, 800)
            ObjectAnimator.ofInt(view.progressBar, "progress", item.acceptedDecibel).setDuration(1000).start()


            view.room_number_tv.text = "${item.roomNumber} 호"
            view.noise_tv.text = item.hateNoiseDescription
            view.smell_tv.text = item.hateSmellDescription
            view.etc_tv.text = item.etc
        }
    }
}