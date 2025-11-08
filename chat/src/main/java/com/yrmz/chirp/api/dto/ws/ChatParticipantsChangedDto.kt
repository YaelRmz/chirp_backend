package com.yrmz.chirp.api.dto.ws

import com.yrmz.chirp.domain.type.ChatId

data class ChatParticipantsChangedDto(
    val chatId: ChatId
)
