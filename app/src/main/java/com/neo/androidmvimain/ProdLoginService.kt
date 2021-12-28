package com.neo.androidmvimain

import com.neo.androidmvimain.LoginRepository
import kotlinx.coroutines.delay

/**
 * simulate login process, and implements the loginRepo
 */
class ProdLoginService: LoginRepository {

    override suspend fun login(email: String, password: String): Boolean {
        delay(2000)   // sim delay in making network request

        return true  // true means login done
    }
}