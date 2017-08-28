package com.domain.mariolopez.testlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domain.mariolopez.testlist.BaseFragment
import com.domain.mariolopez.testlist.api.model.Listing
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import com.domain.mariolopez.testlist.ui.screens.IdFragmentUI
import com.domain.mariolopez.testlist.util.RxBus
import com.github.salomonbrys.kodein.instance
import io.reactivex.rxkotlin.plusAssign

class IdFragment : BaseFragment<IdFragment.ViewModel>() {

    interface ViewModel : com.domain.mariolopez.testlist.ui.ViewModel {

        //consumers
        fun showId(adId: String)
    }

    var ui: IdFragmentUI? = null

    private lateinit var idPresenter: IdPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = container?.let { IdFragmentUI(container) }
        retainInstance = true
        return ui?.inflate()?.setup()
    }

    private fun View.setup(): View {
        return this
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idPresenter = IdPresenter()
        idPresenter.bindView(viewModel)
    }

    override fun onDestroyView() {
        idPresenter.unbindView(viewModel)
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override val viewModel: ViewModel by lazy {
        object : ViewModel {

            override fun showId(adId: String) {
                ui?.idTv?.text = adId
            }

        }
    }
}

class IdPresenter : Presenter<IdFragment.ViewModel>() {

    private val busEvent: RxBus by kodein.instance()

    override fun bindReactive() {
        toDispose += busEvent.rxBus
                .filter { it is Listing }
                .map { it as Listing }
                .subscribe {
                    view?.showId((it.adId))
                }
    }
}
