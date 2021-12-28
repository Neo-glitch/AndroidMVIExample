package com.neo.androidmvimain.ui.login

import com.neo.androidmvimain.redux.Action


/**
 * actions to be performed on LoginScreen
 */
sealed class LoginAction: Action {
    data class EmailChanged(val newEmail: String): LoginAction()
    data class PasswordChanged(val newPassword: String): LoginAction()
    object SignInButtonClicked: LoginAction()
    object LoginStarted: LoginAction()
    object LoginCompleted: LoginAction()
    data class LoginFailed(val error: Throwable): LoginAction()
}