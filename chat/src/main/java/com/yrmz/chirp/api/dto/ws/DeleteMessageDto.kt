package com.yrmz.chirp.api.dto.ws

import com.yrmz.chirp.domain.type.ChatId
import com.yrmz.chirp.domain.type.ChatMessageId

data class DeleteMessageDto(
    val chatId: ChatId,
    val messageId: ChatMessageId
)
