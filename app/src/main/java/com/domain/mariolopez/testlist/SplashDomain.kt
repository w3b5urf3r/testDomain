package com.domain.mariolopez.testlist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.doAsync


class SplashDomain : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        doAsync {
            Thread.sleep(2000)
            if(resources.getBoolean(R.bool.isTablet)){
                startActivity(Intent(applicationContext, TabletActivity::class.java))
            }else{
                startActivity(Intent(applicationContext, HandsetActivity::class.java))
            }
            finish()
        }
    }
}