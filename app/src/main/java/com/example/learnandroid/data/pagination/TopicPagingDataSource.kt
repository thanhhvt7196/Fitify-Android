package com.example.learnandroid.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.learnandroid.data.entity.TopicEntity
import com.example.learnandroid.domain.repository.PhotoRepository

class TopicPagingDataSource constructor(private val photoRepository: PhotoRepository): PagingSource<Int, TopicEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopicEntity> {
        try {
            val page = params.key ?: 1
            val data = photoRepository.getTopics(page)
            val nextPage = if (data.isNotEmpty()) {
                page + 1
            } else {
                null
            }
            return LoadResult.Page(data = data, nextKey = nextPage, prevKey = null)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TopicEntity>): Int? {
        return null;
    }
}