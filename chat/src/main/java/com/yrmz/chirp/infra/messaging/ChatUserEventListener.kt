package com.yrmz.chirp.infra.messaging

import com.yrmz.chirp.domain.events.user.UserEvent
import com.yrmz.chirp.domain.models.ChatParticipant
import com.yrmz.chirp.service.ChatParticipantService
import com.yrmz.chirp.infra.message_queue.MessageQueues
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class ChatUserEventListener(
    private val chatParticipantService: ChatParticipantService,
) {
    @RabbitListener(queues = [MessageQueues.CHAT_USER_EVENTS])
    fun handleUserEvent(event: UserEvent) {
        when (event) {
            is UserEvent.Verified -> {
                chatParticipantService.createChatParticipant(
                    chatParticipant = ChatParticipant(
                        userId = event.userId,
                        username = event.username,
                        email = event.email,
                        profilePictureUrl = null
                    )
                )
            }
            else -> Unit
        }
    }
}
