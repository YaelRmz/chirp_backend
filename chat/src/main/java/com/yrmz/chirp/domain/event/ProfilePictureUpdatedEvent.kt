package com.yrmz.chirp.domain.event

import com.yrmz.chirp.domain.type.UserId

data class ProfilePictureUpdatedEvent(
    val userId: UserId,
    val newUrl: String?
)
