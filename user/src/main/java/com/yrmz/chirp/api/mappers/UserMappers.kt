package com.yrmz.chirp.api.mappers

import com.yrmz.chirp.api.dto.AuthenticatedUserDto
import com.yrmz.chirp.api.dto.UserDto
import com.yrmz.chirp.domain.model.AuthenticatedUser
import com.yrmz.chirp.domain.model.User

fun AuthenticatedUser.toAuthenticatedUserDto(): AuthenticatedUserDto {
    return AuthenticatedUserDto(
        user = user.toUserDto(),
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasEmailVerified
    )
}
