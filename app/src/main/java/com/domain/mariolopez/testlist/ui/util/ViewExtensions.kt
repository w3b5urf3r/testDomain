package com.domain.mariolopez.testlist.ui.util

import android.support.annotation.StyleRes
import android.support.v4.widget.TextViewCompat
import android.widget.TextView


fun TextView.setTextAppearanceC(@StyleRes textAppearance: Int)
        = TextViewCompat.setTextAppearance(this, textAppearance)
