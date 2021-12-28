package com.neo.androidmvimain


/**
 * custom implementation of [LoginRepository] that allow us in test
 * to determine is login should return success or failure scenario by setting the
 * [shouldMockSuccess] boolean
 */
class FakeLoginRepository: LoginRepository {

    var shouldMockSuccess: Boolean = true

    override suspend fun login(email: String, password: String): Boolean {
        return shouldMockSuccess
    }
}