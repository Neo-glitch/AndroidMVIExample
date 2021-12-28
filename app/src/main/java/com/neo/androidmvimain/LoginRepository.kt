package com.neo.androidmvimain

interface LoginRepository {

    suspend fun login(email: String, password: String): Boolean
}