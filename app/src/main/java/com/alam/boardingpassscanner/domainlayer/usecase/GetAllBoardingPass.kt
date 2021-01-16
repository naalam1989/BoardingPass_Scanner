package com.alam.boardingpassscanner.domainlayer.usecase

import androidx.lifecycle.LiveData
import com.alam.boardingpassscanner.datalayer.database.RoomClient
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity

class GetAllBoardingPass {

    fun use(): LiveData<List<BoardingPassEntity>> {
        return RoomClient.getRoomClient().getAllCBoardingPass()
    }
}