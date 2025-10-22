package com.yrmz.chirp.api.controllers

import com.yrmz.chirp.api.dto.ChatDto
import com.yrmz.chirp.api.dto.CreateChatRequest
import com.yrmz.chirp.api.mappers.toChatDto
import com.yrmz.chirp.api.util.requestUserId
import com.yrmz.chirp.infra.database.service.ChatService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/chat")
class ChatController(
    private val chatService: ChatService,
) {

    @PostMapping
    fun createChat(
        @Valid @RequestBody body: CreateChatRequest
    ): ChatDto {
        return chatService.createChat(
            creatorId = requestUserId,
            otherUserIds = body.otherUserIds.toSet()
        ).toChatDto()
    }
}
