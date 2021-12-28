package com.neo.androidmvimain.ui.login

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage

import org.junit.Test


/**
 * test class for Testing that if an action and state is passed to the reducer
 * the expected state will be returned
 *
 * n.b: we didn't test every action, but when writing a test script wise to do this
 */
class LoginReducerTest{


    @Test
    fun emailChangedUpdateEmail(){
        val inputState = LoginViewState()
        val inputAction = LoginAction.EmailChanged("dummyemail@gmail.com")

        val expectedState = inputState.copy(email= "dummyemail@gmail.com")

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        // assertion using truth lib. test for state
        assertThat(newState).isEqualTo(expectedState)
    }

    @Test
    fun loginStartedShowProgressBar(){
        val inputState = LoginViewState()
        val inputAction = LoginAction.LoginStarted

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)


        assertThat(newState.showProgressBar).isTrue()   // checks to know is this state prop is true

        val expectedState = inputState.copy(showProgressBar = true)
        assertThat(newState).isEqualTo(expectedState)
    }


    /*
    test to check that if the action that we made to just return unchnaged state is correct
     */
    @Test
    fun unsupportedActionReturnsOriginalState(){
        val inputState = LoginViewState()
        val inputAction = LoginAction.SignInButtonClicked

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        val expectedState = inputState.copy()
        assertThat(newState).isEqualTo(expectedState)
    }
}