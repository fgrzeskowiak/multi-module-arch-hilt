package com.filippo.core.images.domain

import coil.request.ImageResult

interface ImagesRepository {
    suspend fun downloadImage(path: String?, size: ImageSize = ImageSize.MEDIUM): ImageResult
}
