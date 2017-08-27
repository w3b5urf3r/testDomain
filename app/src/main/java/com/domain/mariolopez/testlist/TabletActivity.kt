package com.domain.mariolopez.testlist

import com.domain.mariolopez.testlist.fragments.ListingsFragment
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import com.maxwellforest.safedome.ui.screens.HandsetActivityUI
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class HandsetActivity : BaseActivity<HandsetActivityUI, HandsetActivity.ViewModel>() {

    interface ViewModel : com.domain.mariolopez.testlist.ui.ViewModel {
        //consumers
        fun navigateTo()

        fun showError(error: Throwable)
    }

    override val ui: HandsetActivityUI = HandsetActivityUI()
    override val presenter by lazy { MainPresenter(Schedulers.io()) }

    override fun initPresenter() {
        presenter.initState()
    }

    override val viewModel: HandsetActivity.ViewModel by lazy {
        object : ViewModel {

            override fun navigateTo() {
                supportFragmentManager.beginTransaction().replace(
                        R.id.mainContainer, ListingsFragment()).commit()
            }

            override fun showError(error: Throwable) {
                showToastError(error)

            }
        }
    }

}

class MainPresenter(scheduler: Scheduler) : Presenter<HandsetActivity.ViewModel>(scheduler) {
    fun initState() {
        view?.navigateTo()
    }

}