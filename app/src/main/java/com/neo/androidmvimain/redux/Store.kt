package com.neo.androidmvimain.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * state container for a given screen
 * @param[initialState] initial state of screen when it is created
 * @param[reducer] sys for taking in current state and new action and returning the new state
 * @param[middlewares] list of middlewares for handling any side effects for actions dispatched to this store
 */
class Store<S : State, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<MiddleWare<S, A>> = emptyList()
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    // done this way to avoid race condition when using state in fun
    private val currentState: S
        get() = _state.value


    /**
     * receives [action] and fires off
     */
    suspend fun dispatch(action: A) {
        // reduce method is ran my class passed to store which will be implementing the reducer
        // and state will be given by class to implementing the state interface

        middlewares.forEach { middleWare ->
            middleWare.process(action, currentState, this)
        }

        val newState = reducer.reduce(currentState, action)
        _state.value = newState
    }
}