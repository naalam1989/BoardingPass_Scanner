package com.alam.boardingpassscanner.presentationlayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity
import com.alam.boardingpassscanner.domainlayer.usecase.GetAllBoardingPass


class MainViewModel : ViewModel() {

    fun getAllBoardingPass(): LiveData<List<BoardingPassEntity>> {
        return GetAllBoardingPass().use()
    }

}