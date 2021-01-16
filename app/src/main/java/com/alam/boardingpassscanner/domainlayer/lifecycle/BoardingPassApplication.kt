package com.alam.boardingpassscanner.domainlayer.lifecycle

import android.app.Application
import androidx.annotation.Keep
import com.alam.boardingpassscanner.datalayer.database.RoomClient

@Keep
class BoardingPassApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Start Room
        RoomClient.startRoom(applicationContext)

    }
}