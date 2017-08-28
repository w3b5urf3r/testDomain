@file:Suppress("IllegalIdentifier")

package com.domain.mariolopez.testlist.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.domain.mariolopez.testlist.*
import com.domain.mariolopez.testlist.ui.screens.HandsetActivityUI
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = TestApp::class)
class BaseActivityTest {


    private lateinit var baseActivityController: ActivityController<TestImplOfBaseActivity>

    @Before
    fun setup() {
        baseActivityController = buildActivity(TestImplOfBaseActivity::class.java)
    }
//    inline fun <reified UI : ActivityAnkoComponent<out AppCompatActivity>> buildActivity(): Class<BaseActivity<UI>> = BaseActivity::class.java as Class<BaseActivity<UI>>

    @Test
    fun `given a BaseActivity I should init ui, view model and presenter view in onCreate()`() {
        val activity = baseActivityController.create().get()
        assertNotNull(activity.ui)
        assertNotNull(activity.viewModel)
        assertNotNull(activity.presenter.view)
    }

    @Test()
    fun `verify toast is shown on showToast`() {
        //when
        val baseActivity = baseActivityController.create().start().get()
        baseActivity.showToastUi("YEAH")
        Assert.assertEquals("YEAH!", ShadowToast.getTextOfLatestToast())
    }
}

//using UI for tablet or smartphone make no difference for the purpose of the test
class TestImplOfBaseActivity : BaseActivity<HandsetActivityUI, HandsetActivity.ViewModel>() {

    override val presenter by lazy { MainPresenter(Schedulers.io()) }
    override val ui: HandsetActivityUI = HandsetActivityUI()
    override val viewModel: HandsetActivity.ViewModel = object : HandsetActivity.ViewModel {
        override fun navigateTo() {
        }

        override fun showToast(message: String) {
            showToast("YEAH")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState, persistentState)
    }
}