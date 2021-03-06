package com.alam.boardingpassscanner.presentationlayer.view.launcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alam.boardingpassscanner.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}