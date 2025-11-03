package com.yrmz.chirp.domain.event

import com.yrmz.chirp.domain.type.ChatId
import com.yrmz.chirp.domain.type.ChatMessageId

data class MessageDeletedEvent(
    val chatId: ChatId,
    val messageId: ChatMessageId,
)
