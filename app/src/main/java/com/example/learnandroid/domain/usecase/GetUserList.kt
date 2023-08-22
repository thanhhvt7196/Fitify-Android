package com.example.learnandroid.domain.usecase

import com.example.learnandroid.data.entity.State
import com.example.learnandroid.data.entity.UserEntity
import com.example.learnandroid.domain.models.User
import com.example.learnandroid.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetUserList @Inject constructor(private val userRepository: UserRepository) :
    UseCase<List<User>, Unit>() {
    override fun buildFlow(param: Unit): Flow<State<List<User>>> {
        var firstUserCollectionFlow = flow {
            emit(userRepository.getUser())
        }
        var secondCollectionFlow = flow {
            emit(userRepository.getUser())
        }
        return firstUserCollectionFlow.zip(secondCollectionFlow) { user1, user2 ->
            val all = mutableListOf<UserEntity>()
            all.addAll(user1)
            all.addAll(user2)
            all.toList().map {
                User.fromEntity(it)
            }
        }.flatMapMerge {
            flow {
                delay(2000)
                emit(State.DataState(it))
            }
        }
    }

}