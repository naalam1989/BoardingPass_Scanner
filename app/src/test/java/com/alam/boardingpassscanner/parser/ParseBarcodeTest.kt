package com.alam.boardingpassscanner.parser

import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity
import com.alam.boardingpassscanner.domainlayer.usecase.ParseBarcode
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class ParseBarcodeTest {

    @Rule
    @JvmField
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val boardingPass = BoardingPassEntity(
        "NADEEM",
        "ALAM",
        "DEL",
        "DOH",
        "QA 375",
        "Feb 18",
        "21J",
        "1",
        "JWC56H"
    )

    private val barcode = "M1ALAM/NADEEM         EJWC56H DELDOHQA 375  049F021J0001 100"

    private lateinit var parseBarcode: ParseBarcode

    @Before
    fun setUp() {
        parseBarcode = ParseBarcode()
    }

    @Test
    fun useTest() {
        val parsedBoardingPass = parseBarcode.use(barcode)
        assertEquals(parsedBoardingPass, boardingPass)
    }

    @Test
    fun useRandomTest() {
        val parsedBoardingPass = parseBarcode.use("this is some random barcode")
        assertEquals(parsedBoardingPass, null)
    }
}