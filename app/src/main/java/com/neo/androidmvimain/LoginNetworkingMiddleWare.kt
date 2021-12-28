package com.neo.androidmvimain

import com.neo.androidmvimain.redux.Action
import com.neo.androidmvimain.redux.MiddleWare
import com.neo.androidmvimain.redux.State
import com.neo.androidmvimain.redux.Store
import com.neo.androidmvimain.ui.login.LoginAction
import com.neo.androidmvimain.ui.login.LoginViewState


/**
 * To signin the user into the app
 */
class LoginNetworkingMiddleWare(
    private val loginRepository: LoginRepository
): MiddleWare<LoginViewState, LoginAction> {

    override fun process(
        action: LoginAction,
        currentState: LoginViewState,
        store: Store<LoginViewState, LoginAction>
    ) {
        when(action){
            // called only when action is still signInButtonClicked, so safe from infinite looping
            is LoginAction.SignInButtonClicked -> {
                store.dispatch(LoginAction.LoginStarted)

                val successful = loginRepository.login(
                    // gets email and password from the currentState
                    email = currentState.email,
                    password = currentState.password
                )

                // since the action must be changed we change the action to be passed to the reducer
                // which acts on this on updates the state
                if(successful)
                    store.dispatch(LoginAction.LoginCompleted)
                else
                    store.dispatch(LoginAction.LoginFailed(null))
            }
        }
    }
}