package com.domain.mariolopez.testlist

import com.domain.mariolopez.testlist.fragments.ListingsFragment
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import com.maxwellforest.safedome.ui.screens.MainActivityUI
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity<MainActivityUI, MainActivity.ViewModel>() {

    interface ViewModel : com.domain.mariolopez.testlist.ui.ViewModel {
        //consumers
        fun navigateTo()

        fun showError(error: Throwable)
    }

    override val ui: MainActivityUI = MainActivityUI()
    override val presenter by lazy { MainPresenter(Schedulers.io()) }

    override fun initPresenter() {
        presenter.initState()
    }

    override val viewModel: MainActivity.ViewModel by lazy {
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

class MainPresenter(scheduler: Scheduler) : Presenter<MainActivity.ViewModel>(scheduler) {
    fun initState() {
        view?.navigateTo()
    }

}