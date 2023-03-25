package org.northsea40.practiceapplication.ViewModel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.northsea40.practiceapplication.Model.User

class MainViewModelImplTest {
    private val ioThreadSurrogate = newSingleThreadContext("IO thread")
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(ioThreadSurrogate)
        mainViewModel = MainViewModelImpl(MockUserDao())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // https://blog.csdn.net/Mr_Tony/article/details/119612968
    @Test
    fun getList() {
        var result = -1
        runBlocking {
            val collectJob = launch(Dispatchers.IO) {
                mainViewModel.users.collect {
                    result = it.size
                }
            }
            mainViewModel.getList()
            delay(100)
            collectJob.cancel()
        }
        assertThat(result, equalTo(0))
    }

    @Test
    fun insert() {
        var result = -1
        runBlocking {
            val collectJob = launch(Dispatchers.IO) {
                mainViewModel.users.collect {
                    println("collect: ${it.size}")
                    result = it.size
                }
            }
            mainViewModel.insert(0, "", "")
            delay(100)
            collectJob.cancel()
            mainViewModel.delete(User(0, "", ""))
        }
        assertThat(result, equalTo(1))
    }

    @Test
    fun delete() {
        var result = -1
        runBlocking {
            val collectJob = launch(Dispatchers.IO) {
                mainViewModel.users.collect {
                    result = it.size
                }
            }
            mainViewModel.insert(0, "", "")
            mainViewModel.delete(User(0, "", ""))
            delay(100)
            collectJob.cancel()
        }
        assertThat(result, equalTo(0))
    }
}