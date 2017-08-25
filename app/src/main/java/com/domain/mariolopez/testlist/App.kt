package com.domain.mariolopez.testlist

import android.app.Application
import com.domain.mariolopez.testlist.di.MainComponent.TestListComponent
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.conf.ConfigurableKodein

/**
 * Created by mariolopez on 25/8/17.
 */

open class App : Application(),KodeinAware{
    override val kodein = ConfigurableKodein()

    override fun onCreate() {
        super.onCreate()

        App.context = this
        kodein.addConfig {
            TestListComponent()
        }
    }

    companion object {
        @JvmField
        var context: App? = null
    }

}