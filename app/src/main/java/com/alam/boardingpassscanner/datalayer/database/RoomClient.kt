package com.alam.boardingpassscanner.datalayer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassDatabase
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity

class RoomClient private constructor(context: Context) {

    private val boardingPassDatabase = Room.databaseBuilder(
        context,
        BoardingPassDatabase::class.java,
        BoardingPassDatabase::class.java.simpleName
    ).build()

    companion object {
        private lateinit var roomClient: RoomClient

        fun startRoom(context: Context) {
            if (!::roomClient.isInitialized) {
                roomClient = RoomClient(context)
            }
        }

        fun getRoomClient() : RoomClient {
            return roomClient
        }
    }

    fun getAllCBoardingPass() : LiveData<List<BoardingPassEntity>> {
        return boardingPassDatabase.boardingPassDao().getAllCBoardingPass()
    }

    suspend fun insertBoardingPass(boardingPassEntity: BoardingPassEntity) {
        boardingPassDatabase.boardingPassDao().insertBoardingPass(boardingPassEntity)
    }

    suspend fun deleteBoardingPass(boardingPassEntity: BoardingPassEntity) {
        boardingPassDatabase.boardingPassDao().deleteBoardingPass(boardingPassEntity)
    }

}