package com.project.segunfrancis.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

/**
 * Created by SegunFrancis
 */

@ExperimentalCoroutinesApi
fun MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
    this.testDispatcher.runBlockingTest {
        block()
    }