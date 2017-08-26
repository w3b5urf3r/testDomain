package com.maxwellforest.safedome.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import com.maxwellforest.common.receiver.PeripheralEventsReceiver
import com.maxwellforest.common.receiver.registerBluetoothFilterBroadcast
import com.maxwellforest.common.receiver.registerPeripheralFilterBroadcast
import com.maxwellforest.common.util.RxBus
import com.maxwellforest.safedome.App
import com.maxwellforest.safedome.Navigation
import com.maxwellforest.safedome.fragments.MapFragment
import com.maxwellforest.safedome.fragments.TutorialFragment
import com.maxwellforest.safedome.receiver.BluetoothStateReceiver
import com.maxwellforest.safedome.ui.ViewModel
import com.maxwellforest.safedome.util.InstrumentationChecker
import com.maxwellforest.ui.ui.activity.ActivityAnkoComponent
import com.tbruyelle.rxpermissions2.RxPermissions
import org.jetbrains.anko.act
import org.jetbrains.anko.bluetoothManager
import org.jetbrains.anko.setContentView

abstract class BaseActivity<out UI : ActivityAnkoComponent<out AppCompatActivity>> : AppCompatActivity() {
    abstract val viewModel: ViewModel
    val kodein: LazyKodein = LazyKodein { App.context!!.kodein }
    val peripheralEventReceiver: PeripheralEventsReceiver by kodein.instance()
    val bluetoothStateReceiver: BluetoothStateReceiver by kodein.instance()
    val busEvent: RxBus by kodein.instance()
    val checker: InstrumentationChecker by kodein.instance()

    companion object {
//        val IMAGE_TRANSITION_NAME = "activity_image_transition"
    }

    abstract val ui: UI
    //the request permission should never be done on [OnResume] make sure that is not triggered in those circumstances
    val rxPermission: RxPermissions by lazy { RxPermissions(this) }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (ui as ActivityAnkoComponent<AppCompatActivity>).setContentView(this)
//        setSupportActionBar(ui.toolbar)

    }


    override fun onStart() {
        super.onStart()
        act.registerPeripheralFilterBroadcast(peripheralEventReceiver)
        act.registerBluetoothFilterBroadcast(bluetoothStateReceiver)
        checker.checks(this, bluetoothManager.adapter, rxPermission)

    }

    override fun onStop() {
        super.onStop()
        act.unregisterReceiver(peripheralEventReceiver)
        act.unregisterReceiver(bluetoothStateReceiver)
    }

}


fun BaseActivity<*>.getFragment(navigation: Navigation.NavigationFragment): Fragment {
    when (navigation.fragmentName) {
        TutorialFragment::class.java.simpleName -> return TutorialFragment(rxPermission, peripheralEventReceiver.observable)
        MapFragment::class.java.simpleName -> return MapFragment(rxPermission, peripheralEventReceiver.observable)
        else -> IllegalStateException("not defined yet")
    }
    return Fragment()
}