package com.yrmz.chirp.api.dto

import com.yrmz.chirp.domain.type.ChatId
import com.yrmz.chirp.domain.type.ChatMessageId
import com.yrmz.chirp.domain.type.UserId
import java.time.Instant

data class ChatMessageDto(
    val id: ChatMessageId,
    val chatId: ChatId,
    val content: String,
    val createdAt: Instant,
    val senderId: UserId
)
