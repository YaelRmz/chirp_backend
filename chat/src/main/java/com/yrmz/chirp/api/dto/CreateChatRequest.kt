package com.yrmz.chirp.api.dto

import com.yrmz.chirp.domain.type.UserId

data class CreateChatRequest(
    @field:Size(
        min = 1,
        message = "Chat must have at least 2 unique participants"
    )
    val otherUserIds: List<UserId>
)
