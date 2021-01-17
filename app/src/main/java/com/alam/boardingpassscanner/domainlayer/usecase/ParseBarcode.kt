package com.alam.boardingpassscanner.domainlayer.usecase

import android.annotation.SuppressLint
import android.util.Log
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity
import java.text.SimpleDateFormat
import java.util.*

class ParseBarcode {

    fun use(barcode: String): BoardingPassEntity? {
        try {
            val name = barcode.substring(2, 22)
            val nameArray = name.split("/").toTypedArray()
            val firstName = nameArray[1]
            val lastName = nameArray[0]
            val booking = barcode.substring(23, 30)
            val fromAirport = barcode.substring(30, 33)
            val toAirport = barcode.substring(33, 36)
            val flightCarrier = barcode.substring(36, 39)
            val flightNumber = barcode.substring(39, 44)
            val flight = "$flightCarrier $flightNumber"
            val julianDate = barcode.substring(44, 47)
            val seat = barcode.substring(48, 52)
            val sequenceNumber = barcode.substring(52, 57)
            val departureDate = convertJulianDate(julianDate.toInt())

            return BoardingPassEntity(
                    firstName.trim(),
                    lastName.trim(),
                    fromAirport.trim(),
                    toAirport.trim(),
                    flight.trim(),
                    departureDate.trim(),
                    if (seat[0] != '0') seat else seat.substring(1, seat.length),
                    Integer.parseInt(sequenceNumber.trim()).toString(),
                    booking.trim()
            )
        } catch (e: Exception) {
            Log.e("Nadeem", "Unable to parse")
        }
        return null
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertJulianDate(day: Int): String {
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        cal.clear()
        cal[Calendar.YEAR] = year
        cal[Calendar.DAY_OF_YEAR] = day
        val formatter = SimpleDateFormat("MMM dd")
        return formatter.format(cal.time)
    }

}