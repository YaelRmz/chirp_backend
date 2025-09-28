package com.yrmz.chirp.domain.exception

class UserNotFoundException: RuntimeException(
    "User not found."
) {
}
