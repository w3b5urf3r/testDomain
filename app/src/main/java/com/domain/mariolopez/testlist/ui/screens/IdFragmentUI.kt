package com.domain.mariolopez.testlist.ui.screens

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.domain.mariolopez.testlist.R
import com.domain.mariolopez.testlist.ui.ViewAnkoComponent
import com.domain.mariolopez.testlist.ui.util.setTextAppearanceC
import org.jetbrains.anko.*

class IdFragmentUI(override val view: ViewGroup) : ViewAnkoComponent<ViewGroup> {

    lateinit var idTv: TextView

    @SuppressLint("PrivateResource")
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        frameLayout {
            backgroundResource = R.color.background_material_light
            idTv= textView {
                gravity = Gravity.CENTER
                setTextAppearanceC(R.style.TextAppearance_AppCompat_Display1)
            }.lparams(matchParent, matchParent)

        }
    }
}