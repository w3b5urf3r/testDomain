package com.domain.mariolopez.testlist

import android.os.Bundle
import com.domain.mariolopez.testlist.api.model.ListingResults
import com.domain.mariolopez.testlist.fragments.ListingsFragment
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import com.maxwellforest.safedome.ui.screens.MainActivityUI
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<MainActivityUI>() {

    interface ViewModel : com.domain.mariolopez.testlist.ui.ViewModel {
        //producers
        val listings: Observable<Unit>

        //consumers
        fun showListings(listings: List<ListingResults>)

        fun navigateTo()

        fun showError(error: Throwable)
    }

    override val ui: MainActivityUI = MainActivityUI()
    val mainPresenter by lazy { MainPresenter(Schedulers.io()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresenter.bindView(viewModel)
        mainPresenter.initState()


    }

    override val viewModel: MainActivity.ViewModel by lazy {
        object : ViewModel {
            override val listings: Observable<Unit>
                get() = TODO("not implemented") //To change initializer of created properties
            // use File | Settings | File Templates.

            override fun showListings(listings: List<ListingResults>) {
//                (ui.recycler.adapter as ListingsAdapter).items = listings
            }

            override fun navigateTo() {
                supportFragmentManager.beginTransaction().replace(
                        R.id.mainContainer, ListingsFragment()).commit()
            }

            override fun showError(error: Throwable) {
                toast(error.message.toString())

            }
        }
    }
}

class MainPresenter(scheduler: Scheduler) : Presenter<MainActivity.ViewModel>(scheduler) {


    override fun bindReactive() {
//        toDispose += busEvent.rxBus
//                .filter { it is Navigation.NavigationFragment }
//                .cast(Navigation.NavigationFragment::class.java)
//                .subscribe {
//                    view?.navigateTo(it)
//                }

    }

    fun initState() {

        view?.navigateTo()

    }

}