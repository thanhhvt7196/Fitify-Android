package com.example.learnandroid.domain.repository

import com.example.learnandroid.data.entity.PhotoEntity
import com.example.learnandroid.data.entity.TopicEntity
import com.example.learnandroid.domain.usecase.DownloadPhoto

interface PhotoRepository {
    suspend fun getTopics(page: Int): List<TopicEntity>
    suspend fun getTopicDetail(topicId: String, page: Int): List<PhotoEntity>
    suspend fun downloadPhoto(url: DownloadPhoto.Params, fileName: String): String
}