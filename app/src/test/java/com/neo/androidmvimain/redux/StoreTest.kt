package com.neo.androidmvimain.redux

import com.google.common.truth.Truth
import com.neo.androidmvimain.TestActionCaptureMiddleWare
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StoreTest{


    /**
     * test to check if we can create a store have it dispatch action and ensure it goes to reducer
     */
    @Test
    fun dispatchSendsActionToReducerAndMiddleWares(){
        // given this
        val inputState = TestState
        val inputAction = TestAction
        val reducer = TestReducer()
        val middleWare = TestActionCaptureMiddleWare<TestState, Action>()

        val store = Store(
            inputState,
             reducer,
            listOf(middleWare)
        )

        //when this happens
        runBlocking {
            store.dispatch(inputAction)
        }

        // then assert
        reducer.assertActionProcessed(inputAction)
        middleWare.assertActionProcessed(inputAction)
    }
}


/**
 * customViewState for our test
 */
object TestState: State


/**
 * custom action for our test
 */
object TestAction: Action


/**
 * custom reducer for our test to deal with test action and state
 */
class TestReducer: Reducer<TestState, Action>{

    private val actionHistory: MutableList<Action> = mutableListOf()

    override fun reduce(currentState: TestState, action: Action): TestState {
        actionHistory.add(action)

        return currentState
    }

    /**
     * fun to assert that an action passe3d as an argument was sent to store
     */
    fun assertActionProcessed(expectedAction: Action){
        Truth.assertThat(actionHistory).contains(expectedAction)
    }
}