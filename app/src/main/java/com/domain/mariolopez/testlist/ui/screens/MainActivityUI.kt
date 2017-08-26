package com.maxwellforest.safedome.ui.screens

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.maxwellforest.safedome.MainActivity
import com.maxwellforest.safedome.R
import com.maxwellforest.ui.ui.activity.ActivityAnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout

/**
 * Created by mariolopez on 27/3/17.
 */
class MainActivityUI : ActivityAnkoComponent<MainActivity> {
    override lateinit var toolbar: Toolbar
    lateinit var recycler: RecyclerView
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
//            appBarLayout {
//                toolbar = toolbar(R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
//                    title = "Safedome 1"
//                    backgroundResource = R.color.primary
//                }.lparams(width = matchParent) {
//                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP or AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
//                }
//            }.lparams(width = matchParent)
            frameLayout {
                id = R.id.mainContainer

            }.lparams(width = matchParent, height = matchParent)
        }

    }

}