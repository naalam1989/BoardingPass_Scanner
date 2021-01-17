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
        var fromAirport: String,

        @ColumnInfo(name = "to")
        var toAirport: String,

        @ColumnInfo(name = "flight")
        var flight: String,

        @ColumnInfo(name = "date")
        var departureDate: String,

        @ColumnInfo(name = "seat")
        var seat: String,

        @ColumnInfo(name = "sequence")
        var sequenceNumber: String,

        @PrimaryKey
        @ColumnInfo(name = "booking")
        var booking: String,
)