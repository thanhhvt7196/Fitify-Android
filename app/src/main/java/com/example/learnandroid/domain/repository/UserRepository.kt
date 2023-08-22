package com.example.learnandroid.domain.repository

import com.example.learnandroid.data.entity.UserEntity

interface UserRepository {
    suspend fun getUser(): List<UserEntity>
}