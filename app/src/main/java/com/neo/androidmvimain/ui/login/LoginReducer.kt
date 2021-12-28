package com.neo.androidmvimain.ui.login

import android.util.Log
import com.neo.androidmvimain.redux.Reducer


/**
 * Responsible for handling [LoginAction] and using that to create
 * a new [LoginViewState]
 */
class LoginReducer: Reducer<LoginViewState, LoginAction> {

    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
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
            is LoginAction.InValidEmailSubmitted -> {
                stateAfterInValidEmailSubmitted(currentState)
            }
            // action that I ain't handling
            else -> currentState
        }
    }

    private fun stateAfterInValidEmailSubmitted(currentState: LoginViewState) =
        currentState.copy(emailError = "Please enter a valid email address")

    private fun stateAfterLoginCompleted(currentState: LoginViewState) =
        currentState.copy(showProgressBar = false, emailError = null)

    private fun stateAfterLoginFailed(currentState: LoginViewState) =
        currentState.copy(showProgressBar = false, emailError = null)

    private fun stateAfterLoginStarted(currentState: LoginViewState) =
        currentState.copy(showProgressBar = true, emailError = null)

    private fun stateWithNewPassword(
        currentState: LoginViewState,
        action: LoginAction.PasswordChanged
    ) = currentState.copy(password = action.newPassword)

    private fun stateWithNewEmail(
        currentState: LoginViewState,
        action: LoginAction.EmailChanged
    ) = currentState.copy(email = action.newEmail)
}