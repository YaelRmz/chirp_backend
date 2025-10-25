package com.yrmz.chirp.domain.exception

import java.lang.RuntimeException

class InvalidChatSizeException: RuntimeException(
    "There must be at least 2 unique participants to create a chat."
)
