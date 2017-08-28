@file:Suppress("IllegalIdentifier")

package com.domain.mariolopez.testlist.presenter

import com.domain.mariolopez.testlist.*
import com.domain.mariolopez.testlist.api.model.Listing
import com.domain.mariolopez.testlist.util.RxBus
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

//note for the reviewer: in Robolectric 3.4 the Build config it's automatically detected
//unfortunately Robolectric is still not compatible with android studio
//git issue https://github.com/robolectric/robolectric/issues/3184
//this base activity code will fail for this reason, code is just put here as showcase
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = TestApp::class)
class MainPresenterTest {

    private lateinit var mainPresenter: MainPresenter

    private lateinit var viewModelMock: HandsetActivity.ViewModel


    private lateinit var busEventMock: RxBus

    private val busSubj: Subject<Any> = PublishSubject.create()

    @Before
    fun beforeEachTest() {
        //given
        busEventMock = mock { on { rxBus } doReturn busSubj }
        RuntimeEnvironment.application.kodeinConfig { bind<RxBus>(overrides = true) with provider { busEventMock } }

        viewModelMock = mock()

        mainPresenter = MainPresenter(Schedulers.trampoline())
        mainPresenter.bindView(viewModelMock)

    }

    @After
    fun `after Each Test `() {
        mainPresenter.unbindView(viewModelMock)
    }

    @Test
    fun `test verify we show toast when we click the listing from handset`() {
        //when
        val mockListing = mock<Listing>{
            on { adId } doReturn "123"
        }
        busEventMock.post(mockListing)

        //given

        //then
        verify(busEventMock, times(1)).post(mockListing)
        verify(viewModelMock, times(1)).showToast(mockListing.adId)

    }


}


