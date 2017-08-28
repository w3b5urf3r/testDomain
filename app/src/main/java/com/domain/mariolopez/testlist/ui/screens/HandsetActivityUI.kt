package com.maxwellforest.safedome.ui.screens

import android.support.design.widget.AppBarLayout
import android.support.v7.widget.Toolbar
import com.domain.mariolopez.testlist.HandsetActivity
import com.domain.mariolopez.testlist.R
import com.domain.mariolopez.testlist.ui.activity.ActivityAnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.appcompat.v7.titleResource
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent

class HandsetActivityUI : ActivityAnkoComponent<HandsetActivity> {

    override lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<HandsetActivity>) = with(ui) {
        coordinatorLayout {
            appBarLayout {
                toolbar = themedToolbar(R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                    titleResource = R.string.test_domain_mario
                    backgroundResource = R.color.primary
                }.lparams(width = matchParent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP or AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(width = matchParent)

            frameLayout {
                id = R.id.mainContainer

            }.lparams(width = matchParent, height = matchParent){
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

}