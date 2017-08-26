package com.maxwellforest.safedome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import com.maxwellforest.common.util.RxBus
import com.maxwellforest.datastore.HardwareDao
import com.maxwellforest.safedome.ui.ViewModel

/**
 * Created by mariolopez on 10/4/17.
 */
abstract class BaseFragment<out VM : ViewModel> : Fragment(){

    abstract val viewModel: VM
    val kodein: LazyKodein = LazyKodein { App.context!!.kodein }
    val hardwareDao: HardwareDao by kodein.instance()

    //    private val answerConstant: String by .instance("answer")
    val busEvent: RxBus by kodein.instance()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    //for better understanding of why this instantiation are done here visit : https://github.com/xxv/android-lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}