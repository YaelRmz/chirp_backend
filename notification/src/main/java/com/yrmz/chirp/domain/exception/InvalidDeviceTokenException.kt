package com.yrmz.chirp.domain.exception

import java.lang.RuntimeException

class InvalidDeviceTokenException: RuntimeException(
    "Invalid device token."
)
