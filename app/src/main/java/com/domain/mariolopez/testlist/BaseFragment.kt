package com.domain.mariolopez.testlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.domain.mariolopez.testlist.ui.ViewModel
import com.github.salomonbrys.kodein.LazyKodein

abstract class BaseFragment<out VM : ViewModel> : Fragment(){

    abstract val viewModel: VM
    val kodein: LazyKodein = LazyKodein { App.context!!.kodein }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}