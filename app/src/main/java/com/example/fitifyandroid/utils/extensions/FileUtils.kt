package com.example.fitifyandroid.utils.extensions

import android.content.Context
import android.os.Environment
import com.example.fitifyandroid.utils.log.Logger
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object FileUtils {
    fun saveFile(context: Context, body: ResponseBody?, path: String): String {
        if (body == null) {
            return ""
        }
        if (!isExternalStorageAvailable() || isExtenalStorageReadOnly()) {
            return ""
        }

        var input: InputStream? = null
        try {
            input = body.byteStream()
            val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/UnsplashDemo")
            if (!directory.exists()) {
                directory.mkdirs()
            }
            val filePath = directory.absolutePath + "/" + path
            val fileOutputStream = FileOutputStream(filePath)
            fileOutputStream.use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return filePath
        } catch (e: Exception) {
            Logger.log("saveFile: $e")
        } finally {
            input?.close()
        }
        return ""
    }

    private fun isExtenalStorageReadOnly(): Boolean {
        val externalStorageState = Environment.getExternalStorageState()
        return externalStorageState == Environment.MEDIA_MOUNTED_READ_ONLY
    }

    private fun isExternalStorageAvailable(): Boolean {
        val externalStorageState = Environment.getExternalStorageState()
        return externalStorageState == Environment.MEDIA_MOUNTED
    }

}