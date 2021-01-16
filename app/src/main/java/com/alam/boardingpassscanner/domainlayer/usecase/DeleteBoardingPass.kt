package com.alam.boardingpassscanner.domainlayer.usecase

import com.alam.boardingpassscanner.datalayer.database.RoomClient
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity

class DeleteBoardingPass {

    suspend fun use(boardingPassEntity: BoardingPassEntity) {
        RoomClient.getRoomClient().deleteBoardingPass(boardingPassEntity)
    }

}