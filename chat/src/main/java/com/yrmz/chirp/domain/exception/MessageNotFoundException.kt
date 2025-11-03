package com.yrmz.chirp.domain.exception

import com.yrmz.chirp.domain.type.ChatMessageId
import java.lang.RuntimeException

class MessageNotFoundException(
    private val id: ChatMessageId
): RuntimeException(
    "Message with $id not found"
) {
}
