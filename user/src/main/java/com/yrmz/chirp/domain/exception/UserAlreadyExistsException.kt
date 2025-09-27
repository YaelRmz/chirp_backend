package com.yrmz.chirp.domain.exception

import java.lang.RuntimeException

class UserAlreadyExistsException: RuntimeException(
    "A user with this user name or email already exists."
)
