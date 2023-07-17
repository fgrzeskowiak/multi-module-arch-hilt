package com.filippo.core.images.data

import android.content.Context
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import com.filippo.core.dispatchers.domain.DispatchersFactory
import com.filippo.core.images.domain.ImageSize
import com.filippo.core.images.domain.ImagesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

internal class ImagesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatchersFactory: DispatchersFactory,
    okHttpClient: OkHttpClient
): ImagesRepository {
    private val imageLoader = ImageLoader.Builder(context)
        .okHttpClient(okHttpClient)
        .build()

    override suspend fun downloadImage(path: String?, size: ImageSize): ImageResult =
        withContext(dispatchersFactory.io()) {
            val request = ImageRequest.Builder(context)
                .data(buildImageUrl(size, path))
                .build()

            imageLoader.execute(request)
        }

    private fun buildImageUrl(size: ImageSize, path: String?) =
        "$IMAGE_REQUEST_BASE_URL/${size.code}/$path"
}

private const val IMAGE_REQUEST_BASE_URL = "https://image.tmdb.org/t/p"
