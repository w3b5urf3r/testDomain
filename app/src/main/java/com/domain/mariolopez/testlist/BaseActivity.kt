package com.domain.mariolopez.testlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.domain.mariolopez.testlist.ui.ViewModel
import com.domain.mariolopez.testlist.ui.activity.ActivityAnkoComponent
import com.github.salomonbrys.kodein.LazyKodein
import org.jetbrains.anko.setContentView

abstract class BaseActivity<out UI : ActivityAnkoComponent<out AppCompatActivity>> : AppCompatActivity() {
    abstract val viewModel: ViewModel
    val kodein: LazyKodein = LazyKodein { App.context!!.kodein }

    companion object {
//        val IMAGE_TRANSITION_NAME = "activity_image_transition"
    }

    abstract val ui: UI

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (ui as ActivityAnkoComponent<AppCompatActivity>).setContentView(this)
        setSupportActionBar(ui.toolbar)

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
    }

}
