package com.comfortment.presentation.ui.main.mai.maiPager

import androidx.cardview.widget.CardView

interface CardAdapter {
    val MAX_ELEVATION_FACTOR: Int
        get() = 8

    fun getBaseElevation(): Float

    fun getCardViewAt(position: Int): CardView?

    fun getCount(): Int
}