package com.yrmz.chirp.domain.event

import com.yrmz.chirp.domain.type.ChatId
import com.yrmz.chirp.domain.type.UserId

data class ChatCreatedEvent(
    val chatId: ChatId,
    val participants: List<UserId>,
)
