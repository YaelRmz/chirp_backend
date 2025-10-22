package com.yrmz.chirp.api.mappers

import com.yrmz.chirp.api.dto.ChatDto
import com.yrmz.chirp.api.dto.ChatMessageDto
import com.yrmz.chirp.api.dto.ChatParticipantDto
import com.yrmz.chirp.domain.models.Chat
import com.yrmz.chirp.domain.models.ChatMessage
import com.yrmz.chirp.domain.models.ChatParticipant

fun Chat.toChatDto(): ChatDto {
    return ChatDto(
        id = id,
        participants = participants.map {
            it.toChatParticipantDto()
        },
        lastActivityAt = lastActivityAt,
        lastMessage = lastMessage?.toChatMessageDto(),
        creator = creator.toChatParticipantDto()
    )
}

fun ChatMessage.toChatMessageDto(): ChatMessageDto {
    return ChatMessageDto(
        id = id,
        chatId = chatId,
        content = content,
        createdAt = createdAt,
        senderId = sender.userId
    )
}

fun ChatParticipant.toChatParticipantDto(): ChatParticipantDto {
    return ChatParticipantDto(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}
