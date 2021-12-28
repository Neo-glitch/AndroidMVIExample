package com.neo.androidmvimain.ui.login

import android.util.Log
import com.neo.androidmvimain.redux.Reducer


/**
 * Responsible for handling [LoginAction] and using that to create
 * a new [LoginViewState]
 */
class LoginReducer: Reducer<LoginViewState, LoginAction> {

    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
        Log.v("Login Reducer", "Processing action : $action")
        return when(action){
            is LoginAction.EmailChanged ->{
                // since state is immutable, make a copy of it
                // and change the email param of that state to be returned
                stateWithNewEmail(currentState, action)
            }
            is LoginAction.PasswordChanged ->{
                stateWithNewPassword(currentState, action)
            }
            is LoginAction.LoginStarted -> {
                stateAfterLoginStarted(currentState)
            }
            is LoginAction.LoginCompleted -> {
                stateAfterLoginCompleted(currentState)
            }
            is LoginAction.LoginFailed -> {
                stateAfterLoginFailed(currentState)
            }
            // action that I ain't handling
            else -> currentState
        }
    }

    private fun stateAfterLoginCompleted(currentState: LoginViewState) =
        stateAfterLoginFailed(currentState)

    private fun stateAfterLoginFailed(currentState: LoginViewState) =
        currentState.copy(showProgressBar = false)

    private fun stateAfterLoginStarted(currentState: LoginViewState) =
        currentState.copy(showProgressBar = true)

    private fun stateWithNewPassword(
        currentState: LoginViewState,
        action: LoginAction.PasswordChanged
    ) = currentState.copy(password = action.newPassword)

    private fun stateWithNewEmail(
        currentState: LoginViewState,
        action: LoginAction.EmailChanged
    ) = currentState.copy(email = action.newEmail)
}