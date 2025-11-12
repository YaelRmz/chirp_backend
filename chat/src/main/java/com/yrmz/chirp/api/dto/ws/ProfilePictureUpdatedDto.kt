package com.yrmz.chirp.api.dto.ws

import com.yrmz.chirp.domain.type.UserId

data class ProfilePictureUpdatedDto(
    val userId: UserId,
    val newUrl: String?
)
