package com.yrmz.chirp.infra.mappers

import com.yrmz.chirp.domain.model.DeviceToken
import com.yrmz.chirp.infra.database.DeviceTokenEntity

fun DeviceTokenEntity.toDeviceToken(): DeviceToken {
    return DeviceToken(
        userId = userId,
        token = token,
        platform = platform.toPlatform(),
        createdAt = createdAt,
        id = id
    )
}
