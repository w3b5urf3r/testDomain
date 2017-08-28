package com.domain.mariolopez.testlist

import com.domain.mariolopez.testlist.fragments.IdFragment
import com.domain.mariolopez.testlist.fragments.ListingsFragment
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import com.domain.mariolopez.testlist.ui.screens.TabletActivityUI
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TabletActivity : BaseActivity<TabletActivityUI, TabletActivity.ViewModel>() {

    interface ViewModel : com.domain.mariolopez.testlist.ui.ViewModel {
        //consumers
        fun navigateTo()

    }

    override val ui: TabletActivityUI = TabletActivityUI()
    override val presenter by lazy { TabletMainPresenter(Schedulers.io()) }

    override fun initPresenter() {
        presenter.initState()
    }

    override val viewModel: TabletActivity.ViewModel by lazy {
        object : ViewModel {

            override fun navigateTo() {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, ListingsFragment(), "CIAO").commit()
                supportFragmentManager.beginTransaction()
                        .replace(R.id.secondPaneContainer, IdFragment()).commit()
            }
        }
    }

}

class TabletMainPresenter(scheduler: Scheduler) : Presenter<TabletActivity.ViewModel>(scheduler) {
    fun initState() {
        view?.navigateTo()
    }

}