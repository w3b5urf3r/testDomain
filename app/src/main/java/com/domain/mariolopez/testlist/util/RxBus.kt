package com.domain.mariolopez.testlist.util

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * RxBus to handle navigation between fragment and back button clicks
 */
class RxBus {
    val rxBus: Subject<Any>

    init {
        val rxEventPublishSubject: PublishSubject<Any> = PublishSubject.create()
        rxBus = rxEventPublishSubject.toSerialized()
    }

    fun post(rxEvent: Any) {
        rxBus.onNext(rxEvent)
    }

    private object Holder {
        val INSTANCE = RxBus()
    }

    companion object create {
        val instance: RxBus by lazy { Holder.INSTANCE }
    }

}