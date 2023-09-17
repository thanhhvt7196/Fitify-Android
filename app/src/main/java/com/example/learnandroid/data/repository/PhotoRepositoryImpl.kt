package com.example.learnandroid.data.repository

import android.content.Context
import com.example.learnandroid.data.entity.PhotoEntity
import com.example.learnandroid.data.entity.TopicEntity
import com.example.learnandroid.data.remote.API
import com.example.learnandroid.domain.repository.PhotoRepository
import com.example.learnandroid.domain.usecase.DownloadPhoto
import com.example.learnandroid.utils.extensions.FileUtils
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val api: API, private val context: Context): PhotoRepository {
    override suspend fun getTopics(page: Int): List<TopicEntity> {
        return api.getTopics(page = page)
    }

    override suspend fun getTopicDetail(topicId: String, page: Int): List<PhotoEntity> {
        return api.getTopicPhotos(topicId = topicId, page = page)
    }

    override suspend fun downloadPhoto(url: DownloadPhoto.Params, fileName: String): String {
        val body = api.downloadPhoto(url)
        return FileUtils.saveFile(context = context, body = body, path = fileName)
    }
}