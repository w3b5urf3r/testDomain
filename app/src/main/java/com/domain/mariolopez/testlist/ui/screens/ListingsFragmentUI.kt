package com.domain.mariolopez.testlist.ui.screens

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.domain.mariolopez.testlist.R
import com.domain.mariolopez.testlist.ui.ViewAnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

class ListingsFragmentUI(override val view: ViewGroup) : ViewAnkoComponent<ViewGroup> {

    lateinit var recycler: RecyclerView

    @SuppressLint("PrivateResource")
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            backgroundResource = R.color.background_material_light
            recycler = recyclerView()
                    .lparams(matchParent, matchParent)

        }
    }
}