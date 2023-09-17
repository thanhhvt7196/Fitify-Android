package com.example.learnandroid.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.learnandroid.data.pagination.PhotoPagingDataSource
import com.example.learnandroid.domain.models.Photo
import com.example.learnandroid.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopicPhotos @Inject constructor(private val photoRepository: PhotoRepository) :
    PagingUseCase<Photo, GetTopicPhotos.Params>() {
    class Params(val topicId: String)

    override fun buildFlow(params: Params): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(20, 5),
            pagingSourceFactory = { PhotoPagingDataSource(photoRepository, params.topicId) }
        ).flow.map {
            it.map {
                Photo.fromEntity(it)
            }
        }
    }
}