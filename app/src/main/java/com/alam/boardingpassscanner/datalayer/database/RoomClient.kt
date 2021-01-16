package com.alam.boardingpassscanner.datalayer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassDatabase
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

class RoomClient private constructor(context: Context) {

    private val boardingPassDatabase = Room.databaseBuilder(
        context,
        BoardingPassDatabase::class.java,
        BoardingPassDatabase::class.java.simpleName
    ).run {
        //Encrypt Database
        //TODO: Use signing keystore
        val passphrase: ByteArray = SQLiteDatabase.getBytes("keystore.passphrase".toCharArray())
        val factory = SupportFactory(passphrase)
        openHelperFactory(factory)
        build()
    }

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

    suspend fun insertBoardingPass(boardingPass: BoardingPassEntity) {
        boardingPassDatabase.boardingPassDao().insertBoardingPass(boardingPass)
    }

    suspend fun deleteBoardingPass(boardingPass: BoardingPassEntity) {
        boardingPassDatabase.boardingPassDao().deleteBoardingPass(boardingPass)
    }

}