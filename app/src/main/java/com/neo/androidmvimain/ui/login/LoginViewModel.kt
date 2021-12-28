package com.neo.androidmvimain.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.androidmvimain.LoggingMiddleWare
import com.neo.androidmvimain.LoginNetworkingMiddleWare
import com.neo.androidmvimain.ProdLoginService
import com.neo.androidmvimain.redux.Store
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * passes events down to where needed and updates the UI with the new state
 *
 * [ViewModel] just send the ui action to the [store] for processing
 */
class LoginViewModel() : ViewModel() {

    private val store = Store(
        initialState = LoginViewState(),  // loginViewState with default params
        reducer = LoginReducer(),
        middlewares = listOf(LoggingMiddleWare(), LoginNetworkingMiddleWare(loginRepository = ProdLoginService()))
    )

    val viewState: StateFlow<LoginViewState> = store.state  // state that ui reacts to, from the store

    fun emailChanged(newEmail: String){
        val action = LoginAction.EmailChanged(newEmail)
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun passwordChanged(newPassword: String){
        val action = LoginAction.PasswordChanged(newPassword)
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun signInButtonClicked(){
        val action = LoginAction.SignInButtonClicked
        viewModelScope.launch {
            store.dispatch(action)
        }
    }
}