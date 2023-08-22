package com.example.learnandroid.domain.models

import com.example.learnandroid.data.entity.UserEntity

class User(val name: String) {
    companion object {
        fun fromEntity(entity: UserEntity): User {
            return User(name = entity.name)
        }
    }
}