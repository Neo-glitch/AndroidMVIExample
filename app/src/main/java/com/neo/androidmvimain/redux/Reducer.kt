package com.neo.androidmvimain.redux



interface Reducer<S: State, A: Action>{

    /**
     * Reducer receives a user [action] and view [currentState]
     * and returns a new state to the view
     */
    fun reduce(currentState: S, action: A): S
}
