package com.neo.androidmvimain

import com.neo.androidmvimain.LoginRepository

/**
 * simulate login process, and implements the loginRepo
 */
class ProdLoginService: LoginRepository {

    override fun login(email: String, password: String): Boolean {
        return true  // true means login done
    }
}