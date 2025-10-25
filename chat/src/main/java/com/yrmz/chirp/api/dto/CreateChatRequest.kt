package com.yrmz.chirp.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.yrmz.chirp.domain.type.UserId
import jakarta.validation.constraints.Size

data class CreateChatRequest(
    @field:Size(
        min = 1,
        message = "Chat must have at least 2 unique participants"
    )
    @JsonProperty("otherUserIds")
    val otherUserIds: List<UserId>
)
