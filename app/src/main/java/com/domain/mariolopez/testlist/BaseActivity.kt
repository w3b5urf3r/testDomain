package com.domain.mariolopez.testlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.domain.mariolopez.testlist.ui.ViewModel
import com.domain.mariolopez.testlist.ui.activity.ActivityAnkoComponent
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import com.github.salomonbrys.kodein.LazyKodein
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

abstract class BaseActivity<out UI : ActivityAnkoComponent<out AppCompatActivity>, VIEW_MODEL : ViewModel> : AppCompatActivity() {

    val kodein: LazyKodein = LazyKodein { App.context!!.kodein }

    companion object {
//        val IMAGE_TRANSITION_NAME = "activity_image_transition"
    }

    abstract val ui: UI
    abstract val presenter: Presenter<VIEW_MODEL>
    abstract val viewModel: VIEW_MODEL

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (ui as ActivityAnkoComponent<AppCompatActivity>).setContentView(this)
        setSupportActionBar(ui.toolbar)
        presenter.bindView(viewModel)
        initPresenter()
    }

    protected open fun initPresenter() {

    }

    protected fun showToastUi(message: String) {
        toast(message)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
    }

}
