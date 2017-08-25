@file:Suppress("IllegalIdentifier")

package com.domain.mariolopez.testlist.presenter

import com.domain.mariolopez.testlist.ui.ViewModel
import com.domain.mariolopez.testlist.ui.presenter.Presenter
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class BasePresenterTest {
    private var presenter: Presenter<ViewModel>? = null
    private var view: ViewModel? = null

    @Before
    fun `before Each Test`() {
        view = object : ViewModel {}
        presenter = Presenter<ViewModel>()
    }

    @After
    fun `after each Test`(){
        view = null
        presenter = null
    }

    @Test
    fun `bind View should Attach View To The Presenter`() {
        presenter!!.bindView(view!!)
        assertThat(presenter!!.view).isSameAs(view)
    }

    @Test
    fun `bind View should Throw If Previous View Is Not Unbounded`() {
        presenter!!.bindView(view!!)

        try {
            presenter!!.bindView(object : ViewModel {})
            failBecauseExceptionWasNotThrown(IllegalStateException::class.java)
        } catch (expected: IllegalStateException) {
            assertThat(expected).hasMessage("Previous view is not unbounded! previousView = " + view!!)
        }

    }

    @Test
    fun `view should Return Null By Default`() {
        assertThat(presenter!!.view).isNull()
    }

    @Test
    fun `dispose Disposables On UnbindView`() {
        presenter!!.bindView(view!!)

        val disposable1 = mock(Disposable::class.java)
        val disposable2 = mock(Disposable::class.java)
        val disposable3 = mock(Disposable::class.java)

        //this needs to be done at presenter level.
        presenter!!.toDispose += disposable1
        presenter!!.toDispose += disposable2
        presenter!!.toDispose += disposable3

        verify(disposable1, never()).dispose()
        verify(disposable2, never()).dispose()
        verify(disposable3, never()).dispose()

        presenter!!.unbindView(view!!)
        verify(disposable1).dispose()
        verify(disposable2).dispose()
        verify(disposable3).dispose()
    }

    @Test
    fun `unbindView should Null TheViewModel Reference`() {
        presenter!!.bindView(view!!)
        assertThat(presenter!!.view).isSameAs(view)

        presenter!!.unbindView(view!!)
        assertThat(presenter!!.view).isNull()
    }

    @Test
    fun `unbindView should Throw If Previous ViewModel Is Not Same As Expected`() {
        presenter!!.bindView(view!!)
        val unexpectedView = object : ViewModel {}

        try {
            presenter!!.unbindView(unexpectedView)
            failBecauseExceptionWasNotThrown(IllegalStateException::class.java)
        } catch (expected: IllegalStateException) {
            assertThat(expected).hasMessage("Unexpected view! previousView = $view, view to unbind = $unexpectedView")
        }

    }
}