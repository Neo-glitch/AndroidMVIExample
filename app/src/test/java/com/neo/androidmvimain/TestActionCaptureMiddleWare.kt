package com.neo.androidmvimain

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.neo.androidmvimain.redux.Action
import com.neo.androidmvimain.redux.MiddleWare
import com.neo.androidmvimain.redux.State
import com.neo.androidmvimain.redux.Store


/**
 * Implementation of [MiddleWare] that will log every action passed to it, still a mock
 *
 * In a unit test, this middleware can be applied in a store and then use this middleware for asserting
 * that an action was sent to that store
 */
class TestActionCaptureMiddleWare<S: State, A: Action>: MiddleWare<S, A> {
    private val actionHistory : MutableList<A> = mutableListOf()


    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        actionHistory.add(action)
    }

    /**
     * fun to assert that an action passe3d as an argument was sent to store
     */
    fun assertActionProcessed(expectedAction: A){
        assertThat(actionHistory).contains(expectedAction)
    }
}