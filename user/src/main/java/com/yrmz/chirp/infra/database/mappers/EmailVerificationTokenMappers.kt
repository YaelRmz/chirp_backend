package com.yrmz.chirp.infra.database.mappers

import com.yrmz.chirp.domain.model.EmailVerificationToken
import com.yrmz.chirp.infra.database.entities.EmailVerificationTokenEntity

fun EmailVerificationTokenEntity.toEmailVerificationToken(): EmailVerificationToken {
    return EmailVerificationToken(
        id = id,
        token = token,
        user = user.toUser()
    )
}
