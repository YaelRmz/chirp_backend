package com.yrmz.chirp.domain.event

import com.yrmz.chirp.domain.type.ChatId
import com.yrmz.chirp.domain.type.UserId

data class ChatParticipantsLeftEvent(
    val chatId: ChatId,
    val userId: UserId
)
