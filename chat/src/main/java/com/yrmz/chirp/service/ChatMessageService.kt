package com.yrmz.chirp.service

import com.yrmz.chirp.domain.event.MessageDeletedEvent
import com.yrmz.chirp.domain.events.chat.ChatEvent
import com.yrmz.chirp.domain.exception.ChatNotFoundException
import com.yrmz.chirp.domain.exception.ChatParticipantNotFoundException
import com.yrmz.chirp.domain.exception.ForbiddenException
import com.yrmz.chirp.domain.exception.MessageNotFoundException
import com.yrmz.chirp.domain.models.ChatMessage
import com.yrmz.chirp.domain.type.ChatId
import com.yrmz.chirp.domain.type.ChatMessageId
import com.yrmz.chirp.domain.type.UserId
import com.yrmz.chirp.infra.database.entities.ChatMessageEntity
import com.yrmz.chirp.infra.database.mappers.toChatMessage
import com.yrmz.chirp.infra.database.repositories.ChatMessageRepository
import com.yrmz.chirp.infra.database.repositories.ChatParticipantRepository
import com.yrmz.chirp.infra.database.repositories.ChatRepository
import com.yrmz.chirp.infra.message_queue.EventPublisher
import jakarta.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val eventPublisher: EventPublisher,
) {


    @Transactional
    @CacheEvict(
        value = ["messages"],
        key = "#chatId",
    )
    fun sendMessage(
        chatId: ChatId,
        senderId: UserId,
        content: String,
        messageId: ChatMessageId? = null
    ): ChatMessage {
        val chat = chatRepository.findChatById(chatId, senderId)
            ?: throw ChatNotFoundException()
        val sender = chatParticipantRepository.findByIdOrNull(senderId)
            ?: throw ChatParticipantNotFoundException(senderId)

        val savedMessage = chatMessageRepository.saveAndFlush(
            ChatMessageEntity(
                id = messageId,
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender,
            )
        )

        eventPublisher.publish(
            event = ChatEvent.NewMessage(
                senderId = sender.userId,
                senderUsername = sender.username,
                recipientsId = chat.participants.map { it.userId }.toSet(),
                chatId = chatId,
                message = savedMessage.content
            )
        )

        return savedMessage.toChatMessage()
    }

    @Transactional
    fun deleteMessage(
        messageId: ChatMessageId,
        requestUserId: UserId
    ) {
        val message = chatMessageRepository.findByIdOrNull(messageId)
            ?: throw MessageNotFoundException(messageId)

        if (message.sender.userId != requestUserId) {
            throw ForbiddenException()
        }

        chatMessageRepository.delete(message)

        applicationEventPublisher.publishEvent(
            MessageDeletedEvent(
                chatId = message.chatId,
                messageId = messageId
            )
        )

        evictMessagesCache(message.chatId)
    }

    @CacheEvict(
        value = ["messages"],
        key = "#chatId",
    )
    fun evictMessagesCache(chatId: ChatId) {
        // NO-OP: Let Spring handle the cache evict
    }
}
