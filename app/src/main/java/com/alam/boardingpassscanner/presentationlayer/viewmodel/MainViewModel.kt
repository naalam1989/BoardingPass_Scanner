package com.alam.boardingpassscanner.presentationlayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity
import com.alam.boardingpassscanner.domainlayer.usecase.DeleteBoardingPass
import com.alam.boardingpassscanner.domainlayer.usecase.GetAllBoardingPass
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    fun getAllBoardingPass(): LiveData<List<BoardingPassEntity>> {
        return GetAllBoardingPass().use()
    }

    fun deleteBoardingPass(boardingPassEntity: BoardingPassEntity) {
        viewModelScope.launch {
            DeleteBoardingPass().use(boardingPassEntity)
        }
    }

}