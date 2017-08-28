package com.domain.mariolopez.testlist.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domain.mariolopez.testlist.BaseFragment
import com.domain.mariolopez.testlist.api.RestAdapter
import com.domain.mariolopez.testlist.api.model.Listing
import com.domain.mariolopez.testlist.ui.adapter.ListingsAdapter
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import com.domain.mariolopez.testlist.ui.screens.ListingsFragmentUI
import com.domain.mariolopez.testlist.util.RxBus
import com.github.salomonbrys.kodein.instance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class ListingsFragment : BaseFragment<ListingsFragment.ViewModel>() {

    interface ViewModel : com.domain.mariolopez.testlist.ui.ViewModel {
        //producers
        fun listingClicks(): Observable<Any>?

        //consumers
        fun showListings(listings: List<Listing>)

        fun saveState(listings: List<Listing>)

    }

    var ui: ListingsFragmentUI? = null

    private lateinit var listingsPresenter: ListingsPresenter
    private val listingAdapter = ListingsAdapter()
    private var listState: List<Listing>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = container?.let { ListingsFragmentUI(container) }
        return ui?.inflate()?.setup()
    }

    private fun View.setup(): View {
        return this
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui?.recycler?.adapter = listingAdapter
        ui?.recycler?.layoutManager = LinearLayoutManager(this.context)
        listingsPresenter = ListingsPresenter()
        listingsPresenter.bindView(viewModel)
    }

    override fun onStart() {
        super.onStart()
    }


    override fun onDestroyView() {
        listingsPresenter.unbindView(viewModel)
        super.onDestroyView()
    }

    override val viewModel: ViewModel by lazy {
        object : ViewModel {
            override fun listingClicks(): Observable<Any>? {
                return (ui?.recycler?.adapter as? ListingsAdapter)?.itemClicks
            }


            override fun showListings(listings: List<Listing>) {
                (ui?.recycler?.adapter as? ListingsAdapter)?.items = listings
            }

            override fun saveState(listings: List<Listing>) {
                listState = listings
            }
        }
    }
}

class ListingsPresenter : Presenter<ListingsFragment.ViewModel>() {

    private val restAdapter: RestAdapter by kodein.instance()
    private val busEvent: RxBus by kodein.instance()

    override fun bindReactive() {

        toDispose += restAdapter.getListings()
                .subscribeOn(Schedulers.io()) // “work” on io thread
                .observeOn(AndroidSchedulers.mainThread()) // “listen” on UIThread
                .subscribe {
                    //todo
                    view?.showListings(it.listings)
                    view?.saveState(it.listings)
//                    Log.d("List", it.listings.toString())
                }

        toDispose += view?.listingClicks()!!.subscribe {
            busEvent.post(it as Listing)
        }
    }
}
