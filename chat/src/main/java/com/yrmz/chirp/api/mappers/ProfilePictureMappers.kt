package com.yrmz.chirp.api.mappers

import com.yrmz.chirp.api.dto.PictureUploadResponse
import com.yrmz.chirp.domain.models.ProfilePictureUploadCredentials

fun ProfilePictureUploadCredentials.toResponse(): PictureUploadResponse {
    return PictureUploadResponse(
        uploadUrl = uploadUrl,
        publicUrl = publicUrl,
        headers = headers,
        expiresAt = expiresAt
    )
}
