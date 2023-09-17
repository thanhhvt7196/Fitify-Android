package com.example.learnandroid.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.learnandroid.data.entity.PhotoEntity
import com.example.learnandroid.domain.repository.PhotoRepository

class PhotoPagingDataSource constructor(private val photoRepository: PhotoRepository, private val topicId: String): PagingSource<Int, PhotoEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoEntity> {
        return try {
            val page = params.key ?: 1
            val data = photoRepository.getTopicDetail(topicId, page)
            val nextKey = if (data.isNotEmpty()) {
                page + 1
            } else {
                null
            }
            LoadResult.Page(data = data, prevKey = null, nextKey = nextKey)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoEntity>): Int? {
        return null
    }
}