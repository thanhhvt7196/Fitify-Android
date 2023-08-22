package com.example.learnandroid.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.learnandroid.domain.models.Topic
import com.example.learnandroid.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopics @Inject constructor(private val photoRepository: PhotoRepository): PagingUseCase<Topic, Unit>() {
    override fun buildFlow(param: Unit): Flow<PagingData<Topic>> {

    }

}