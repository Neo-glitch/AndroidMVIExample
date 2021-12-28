package com.neo.androidmvimain.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * state container for a given screen
 * @param[initialState] initial state of screen when it is created
 * @param[reducer] sys for taking in current state and new action and returning the new state
 */
class Store<S: State, A: Action>(
    initialState: S,
    private val reducer: Reducer<S, A>
) {

    private val _state =  MutableStateFlow(initialState)
    val state: StateFlow<S> = _state


    /**
     * receives [action] and fires off
     */
    fun dispatch(action: A){
        val currentState = _state.value
        // reduce method is ran my class passed to store which will be implementing the reducer
        // and state will be given by class to implementing the state interface
        val newState = reducer.reduce(currentState, action)
        _state.value = newState
    }
}