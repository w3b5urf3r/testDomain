package com.domain.mariolopez.testlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.domain.mariolopez.testlist.api.RestAdapter
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val kodein: LazyKodein = LazyKodein { App.context!!.kodein }

    val restAdapter: RestAdapter by kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        restAdapter.getListings()
                .subscribeOn(Schedulers.io()) // “work” on io thread
                .observeOn(AndroidSchedulers.mainThread()) // “listen” on UIThread
                .subscribe {
                    //todo
                }

    }
}
