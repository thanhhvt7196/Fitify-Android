package com.example.learnandroid.data.repository

import com.example.learnandroid.data.entity.UserEntity
import com.example.learnandroid.data.remote.API
import com.example.learnandroid.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: API): UserRepository {
    override suspend fun getUser(): List<UserEntity> {
        return  api.getUsers()
    }
}