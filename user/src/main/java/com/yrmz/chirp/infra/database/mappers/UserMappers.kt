package com.yrmz.chirp.infra.database.mappers

import com.yrmz.chirp.domain.model.User
import com.yrmz.chirp.infra.database.entities.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id = id!!,
        username = username,
        email = email,
        hasEmailVerified = hasVerifiedEmail
    )
}
