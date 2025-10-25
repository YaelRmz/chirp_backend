package com.yrmz.chirp.domain.exception

import java.lang.RuntimeException

class ChatNotFoundException: RuntimeException(
    "Chat not found"
) {
}
