package com.example.learnandroid.domain.usecase

import com.example.learnandroid.data.entity.State
import com.example.learnandroid.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DownloadPhoto @Inject constructor(private val photoRepository: PhotoRepository): UseCase<String, DownloadPhoto.Params>() {
    class Params(val url: String, val fileName: String)

    override fun buildFlow(params: Params): Flow<State<String>> {
        return flow {
            emit(State.DataState(photoRepository.downloadPhoto(url = params, fileName = params.fileName)))
        }
    }
}