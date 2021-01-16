package com.alam.boardingpassscanner.datalayer.database.room

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class BoardingPassEntity(

    @ColumnInfo(name = "firstname")
    var firstName: String,

    @ColumnInfo(name = "lastname")
    var lastName: String,

    @ColumnInfo(name = "from")
    var from: String,

    @ColumnInfo(name = "to")
    var to: String,

    @ColumnInfo(name = "flight")
    var flight: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "seat")
    var seat: String,

    @ColumnInfo(name = "sequence")
    var sequence: String,

    @PrimaryKey
    @ColumnInfo(name = "booking")
    var booking: String,
)