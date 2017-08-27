package com.domain.mariolopez.testlist.ui.layouts

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.domain.mariolopez.testlist.R
import org.jetbrains.anko.*

fun _LinearLayout.addressTextView(): TextView {
    return textView {
        setTextColor(Color.BLACK)
        maxLines = 2
    }.lparams(height = wrapContent, width = matchParent) {
        topMargin = dip(10)
    }
}

fun _LinearLayout.roomsTextView(): TextView {
    return textView {
        typeface = Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        maxLines = 1
    }.lparams(height = wrapContent, width = matchParent) {
        topMargin = dip(10)
    }
}

fun _LinearLayout.priceTextView(): TextView {
    return textView {
        typeface = Typeface.DEFAULT_BOLD
        maxLines = 1
        setTextColor(ContextCompat.getColor(context, R.color.accent))
    }.lparams(height = wrapContent, width = matchParent) {
        topMargin = dip(10)
    }
}
