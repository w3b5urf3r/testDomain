package com.maxwellforest.safedome.ui

import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext


/**
 * Created by mariolopez on 28/3/17.
 */
interface ViewAnkoComponent<T : ViewGroup> : AnkoComponent<T> {

    val view: T

    fun inflate(): View {
        val ctx = AnkoContext.Companion.create(view.context, view)
        return createView(ctx)
    }
}