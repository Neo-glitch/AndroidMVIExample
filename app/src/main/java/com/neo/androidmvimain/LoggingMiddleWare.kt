package com.neo.androidmvimain

import android.util.Log
import com.neo.androidmvimain.redux.Action
import com.neo.androidmvimain.redux.MiddleWare
import com.neo.androidmvimain.redux.State
import com.neo.androidmvimain.redux.Store
import com.neo.androidmvimain.ui.login.LoginAction
import com.neo.androidmvimain.ui.login.LoginViewState


/**
 * Just for logging stuff on logcat
 */
class LoggingMiddleWare<S: State, A: Action>: MiddleWare<S, A> {

    override fun process(action: A, currentState: S, store: Store<S, A>) {
        Log.v("LoggingMiddleWare", "Processing action: $action, currentState: $currentState")
    }
}