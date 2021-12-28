package com.neo.androidmvimain.ui.login

import com.neo.androidmvimain.redux.State


/**
 * all state to be on the LoginFragment
 *  n.b : could use sealed class, but is harder
 */
data class LoginViewState(
    // we could avoid using default values though i.e = ""
    // instead of showProgressBar, we could use 'loading'
    val email: String = "",
    val password: String = "",
    val showProgressBar: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null
) : State
