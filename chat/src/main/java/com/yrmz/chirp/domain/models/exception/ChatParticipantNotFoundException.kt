package com.yrmz.chirp.domain.models.exception

import com.yrmz.chirp.domain.type.UserId

class ChatParticipantNotFoundException(
    private val id: UserId
): RuntimeException(
    "The chat participant with the ID $id was not found"
) {
}
