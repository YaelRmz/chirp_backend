package com.yrmz.chirp.api.dto.ws

import com.fasterxml.jackson.annotation.JsonProperty
import com.yrmz.chirp.domain.type.ChatId
import com.yrmz.chirp.domain.type.ChatMessageId

data class SendMessageDto(
    @JsonProperty("chatId")
    val chatId: ChatId,
    @JsonProperty("content")
    val content: String,
    @JsonProperty("messageId")
    val messageId: ChatMessageId? = null
)
