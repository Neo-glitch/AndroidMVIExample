package com.neo.androidmvimain

interface LoginRepository {

    fun login(email: String, password: String): Boolean
}