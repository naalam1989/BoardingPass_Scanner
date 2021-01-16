package com.alam.boardingpassscanner.datalayer.database.room

import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import androidx.room.*

@Keep
@Dao
interface BoardingPassDao {

    @Query("SELECT * FROM BoardingPassEntity")
    fun getAllCBoardingPass() : LiveData<List<BoardingPassEntity>>

    @Delete
    suspend fun deleteBoardingPass(boardingPass: BoardingPassEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoardingPass(boardingPass: BoardingPassEntity)
}