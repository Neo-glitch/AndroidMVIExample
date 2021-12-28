package com.neo.androidmvimain.redux


/**
 * [MiddleWare] accepts action and state and pass the state unchanged but the action might be
 * changed or mutated before getting to the Reducer
 * action is only emitted to store again, only when the action has changed e.g from buttonClick
 * to LoginStartedAction
 *
 * [MiddleWare] deals with side effects of action like triggering network calls, db calls
 */
interface MiddleWare <S: State, A: Action>{

    /**
     * process[action] and [currentState] and determines if we wanna perform any side effects or
     * trigger a new action
     *
     * @param[store] only called when a new action is needed else leave it don't call it
     */
    fun process(action: A,
                 currentState: S,
                 store: Store<S, A>)
}