package com.domain.mariolopez.testlist.ui.presenter
import android.support.annotation.CallSuper
import com.domain.mariolopez.testlist.App
import com.github.salomonbrys.kodein.LazyKodein
import com.domain.mariolopez.testlist.ui.ViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalStateException

/**
 * Base presenter implementation.

 * @param <VIEW_MODEL> view.
</VIEW_MODEL> */
open class Presenter<VIEW_MODEL : ViewModel>(val scheduler: Scheduler = Schedulers.io()) {

    val kodein: LazyKodein = LazyKodein { App.context!!.kodein }
    val toDispose = CompositeDisposable()
    @Volatile var view: VIEW_MODEL? = null

    /**
     * bind the view with current presenter
     */
    @CallSuper
    open fun bindView(view: VIEW_MODEL) {
        val previousView = this.view

        if (previousView != null) {
            throw IllegalStateException("Previous view is not unbounded! previousView = " + previousView)
        }

        this.view = view
        bindReactive()
    }

    open fun bindReactive() {}

    /**
     * unbind the [VIEW_MODEL] with the Presenter class and dispose all [Disposable]
     */
    @CallSuper
    fun unbindView(view: VIEW_MODEL) {
        val previousView = this.view

        if (previousView === view) {
            this.view = null
        } else {
            throw IllegalStateException("Unexpected view! previousView = $previousView, view to unbind = $view")
        }

        // Dispose all subscriptions that need to be disposed in this lifecycle state.
        toDispose.clear()
    }
}
