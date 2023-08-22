package com.example.learnandroid.domain.models

import com.example.learnandroid.data.entity.PhotoEntity
import java.io.Serializable

open class Photo(val id: String, val raw: String, val full: String, val thumb: String) :
    Serializable {
    var topLeftRadius: Float = 15f
    var topRightRadius: Float = 15f
    var bottomLeftRadius: Float = 15f
    var bottomRightRadius: Float = 15f

    companion object {
        fun fromEntity(entity: PhotoEntity): Photo {
            return Photo(
                id = entity.id ?: "",
                raw = entity.urls?.raw ?: "",
                full = entity.urls?.full ?: "",
                thumb = entity.urls?.thumb ?: ""
            )
        }
    }
}