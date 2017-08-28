package com.domain.mariolopez.testlist.di

import com.domain.mariolopez.testlist.api.RestAdapter
import com.domain.mariolopez.testlist.util.RxBus
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider

object MainComponent {

    fun Kodein.Builder.TestListComponent() {

        bind<RestAdapter>() with provider { RestAdapter() }
        bind<RxBus>() with provider { RxBus.create.instance }

    }
}