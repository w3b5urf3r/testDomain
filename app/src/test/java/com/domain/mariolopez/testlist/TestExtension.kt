package com.domain.mariolopez.testlist

import android.app.Application
import com.github.salomonbrys.kodein.Kodein

internal fun Application.kodeinConfig(config: Kodein.Builder.() -> Unit) {
    (this as TestApp).kodein.addConfig { config() }
}
