package com.neo.androidmvimain

import com.neo.androidmvimain.redux.Store
import com.neo.androidmvimain.ui.login.LoginAction
import com.neo.androidmvimain.ui.login.LoginReducer
import com.neo.androidmvimain.ui.login.LoginViewState
import kotlinx.coroutines.runBlocking
import org.junit.Test


/**
 * Test for the LoginNetworkingMiddleWare
 * more complex thn the reducer Test, but mocking helps us for dependencies
 *
 * could have used mockito for mocking, but we create the fakes by ourself
 */
class LoginNetworkingMiddleWareTest{


    /**
     * test to ensure that after having [LoginNetworkingMiddleWare] process the [LoginAction.InValidEmailSubmitted]
     * action when, no email test found. the store should also should have processed the
     * [LoginAction.InValidEmailSubmitted] action too
     */
    @Test
    fun submitInvalidEmail(){
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = true

        val inputState = LoginViewState(email = "")   // since invalid email runs only when email is empty string
        val action = LoginAction.SignInButtonClicked

        // mocks networking middleware
        val middleWareUnderTest = LoginNetworkingMiddleWare(fakeLoginRepository)

        // used to assert that action was passed to store
        val actionCaptureMiddleWare = TestActionCaptureMiddleWare<LoginViewState, LoginAction>()

        val loginStore = Store(
            inputState, LoginReducer(),
            listOf(actionCaptureMiddleWare)
        )

        // when. n.b: run blocking is used for running coroutines in test only, don't use in
        // production code
        runBlocking {
            middleWareUnderTest.process(action, inputState, loginStore)
        }

        //then (assertion)
        actionCaptureMiddleWare.assertActionProcessed(LoginAction.InValidEmailSubmitted)
    }


    /**
     * when [LoginNetworkingMiddleWare] processes [LoginAction.SignInButtonClicked] action and valid
     * inputs found, ensure that the corresponding actions are procesed by the store
     */
    @Test
    fun submitValidEmailCompleted(){
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = true

        val inputState = LoginViewState(email = "testEmail@gmail.com", password= "password")
        val action = LoginAction.SignInButtonClicked

        // mocks networking middleware
        val middleWareUnderTest = LoginNetworkingMiddleWare(fakeLoginRepository)

        // used to assert that action was passed to store
        val actionCaptureMiddleWare = TestActionCaptureMiddleWare<LoginViewState, LoginAction>()

        val loginStore = Store(
            inputState, LoginReducer(),
            listOf(actionCaptureMiddleWare)
        )

        // when. n.b: run blocking is used for running coroutines in test only, don't use in
        // production code
        runBlocking {
            middleWareUnderTest.process(action, inputState, loginStore)
        }

        //then (assertion)
        actionCaptureMiddleWare.assertActionProcessed(LoginAction.LoginStarted)
        actionCaptureMiddleWare.assertActionProcessed(LoginAction.LoginCompleted)
    }


    @Test
    fun submitValidEmailFailed(){
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = false

        val inputState = LoginViewState(email = "testEmail@gmail.com", password= "password")
        val action = LoginAction.SignInButtonClicked

        // mocks networking middleware
        val middleWareUnderTest = LoginNetworkingMiddleWare(fakeLoginRepository)

        // used to assert that action was passed to store
        val actionCaptureMiddleWare = TestActionCaptureMiddleWare<LoginViewState, LoginAction>()

        val loginStore = Store(
            inputState, LoginReducer(),
            listOf(actionCaptureMiddleWare)
        )

        // when. n.b: run blocking is used for running coroutines in test only, don't use in
        // production code
        runBlocking {
            middleWareUnderTest.process(action, inputState, loginStore)
        }

        //then (assertion)
        actionCaptureMiddleWare.assertActionProcessed(LoginAction.LoginFailed(null))
    }

}