package com.alam.boardingpassscanner.databasetest

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alam.boardingpassscanner.coroutine.CoroutineTestRule
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassDao
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassDatabase
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*


@RunWith(AndroidJUnit4::class)
class BoardingPassDaoTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    private lateinit var observer: Observer<List<BoardingPassEntity>>

    private lateinit var database: BoardingPassDatabase
    private lateinit var dao: BoardingPassDao

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

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, BoardingPassDatabase::class.java)
            .allowMainThreadQueries().build()
        dao = database.boardingPassDao()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAllCBoardingPassTest() = runBlockingTest {

        dao.getAllCBoardingPass().observeForever(observer)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertBoardingPassTest() = runBlockingTest {

        dao.getAllCBoardingPass().observeForever(observer)

        dao.insertBoardingPass(boardingPass)

        verify(observer)?.onChanged(Collections.singletonList(boardingPass))

    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteBoardingPassTest() = runBlockingTest {

        dao.getAllCBoardingPass().observeForever(observer)
        dao.insertBoardingPass(boardingPass)

        dao.deleteBoardingPass(boardingPass)

        verify(observer)?.onChanged(Collections.singletonList(boardingPass))

    }

}