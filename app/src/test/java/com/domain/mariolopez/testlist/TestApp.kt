package com.domain.mariolopez.testlist

import com.domain.mariolopez.testlist.di.MainComponent.TestListComponent
import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method

class TestApp : App(), TestLifecycleApplication {

    override fun onCreate() {
        this.kodein.mutable = true
        super.onCreate()
    }

    override fun beforeTest(method: Method) {
        this.kodein.clear()
        this.kodein.addConfig { TestListComponent() }
    }

    override fun prepareTest(test: Any) {}

    override fun afterTest(method: Method) {}
}