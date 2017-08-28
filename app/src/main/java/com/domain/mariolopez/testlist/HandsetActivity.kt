package com.domain.mariolopez.testlist

import com.domain.mariolopez.testlist.api.model.Listing
import com.domain.mariolopez.testlist.fragments.ListingsFragment
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import com.domain.mariolopez.testlist.ui.screens.HandsetActivityUI
import com.domain.mariolopez.testlist.util.RxBus
import com.github.salomonbrys.kodein.instance
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class HandsetActivity : BaseActivity<HandsetActivityUI, HandsetActivity.ViewModel>() {

    interface ViewModel : com.domain.mariolopez.testlist.ui.ViewModel {
        //consumers
        fun navigateTo()

        fun showToast(message: String)
    }

    override val ui: HandsetActivityUI = HandsetActivityUI()
    override val presenter by lazy { MainPresenter(Schedulers.io()) }

    override fun initPresenter() {
        presenter.initState()
    }

    override val viewModel: HandsetActivity.ViewModel by lazy {
        object : ViewModel {

            override fun navigateTo() {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, ListingsFragment()).commit()
            }

            override fun showToast(message: String) {
                showToast(message)

            }
        }
    }

}

class MainPresenter(scheduler: Scheduler) : Presenter<HandsetActivity.ViewModel>(scheduler) {
    private val busEvent: RxBus by kodein.instance()
    override fun bindReactive() {
        super.bindReactive()
        toDispose += busEvent.rxBus
                .filter { it is Listing }
                .map { it as Listing }
                .subscribe {
                    view?.showToast(it.adId)
                }
    }
    fun initState() {
        view?.navigateTo()
    }

}