package com.yrmz.chirp.api.util

import com.yrmz.chirp.domain.exception.UnauthorizedException
import com.yrmz.chirp.domain.model.UserId
import org.springframework.security.core.context.SecurityContextHolder

val requestUserId: UserId
    get() = SecurityContextHolder.getContext().authentication?.principal as? UserId
        ?: throw UnauthorizedException()
