package com.example.learnandroid.domain.repository

import com.example.learnandroid.data.entity.PhotoEntity
import com.example.learnandroid.data.entity.TopicEntity

interface PhotoRepository {
    suspend fun getTopics(page: Int): List<TopicEntity>
    suspend fun getTopicDetail(topicId: String, page: Int): List<PhotoEntity>
    suspend fun downloadPhoto(url: String, fileName: String): String
}