package com.domain.mariolopez.testlist.di

import com.domain.mariolopez.testlist.api.ApiManager
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider

/**
 * Created by mariolopez on 25/8/17.
 */
object MainComponent {

    fun Kodein.Builder.TestListComponent() {
        //Api  manager
        bind<ApiManager>() with provider { ApiManager() }
    }
}