package com.neo.androidmvimain.ui.login

import androidx.lifecycle.ViewModel
import com.neo.androidmvimain.redux.Store
import kotlinx.coroutines.flow.StateFlow


/**
 * passes events down to where needed and updates the UI with the new state
 *
 * [ViewModel] just send the ui action to the [store] for processing
 */
class LoginViewModel() : ViewModel() {

    private val store = Store(
        initialState = LoginViewState(),  // loginViewState with default params
        reducer = LoginReducer()
    )

    val viewState: StateFlow<LoginViewState> = store.state  // state that ui reacts to, from the store

    fun emailChanged(newEmail: String){
        val action = LoginAction.EmailChanged(newEmail)
        store.dispatch(action)
    }

    fun passwordChanged(newPassword: String){
        val action = LoginAction.PasswordChanged(newPassword)
        store.dispatch(action)
    }

    fun signInButtonClicked(){
        val action = LoginAction.SignInButtonClicked
        store.dispatch(action)
    }
}