package com.yrmz.chirp.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.yrmz.chirp.domain.type.UserId
import jakarta.validation.constraints.Size

data class AddParticipantToChatDto(
    @field:Size(min = 1)
    @JsonProperty("userIds")
    val userIds: List<UserId>
)
