package com.yrmz.chirp.domain.exception

class UnauthorizedException: RuntimeException(
    "Missing auth details"
) {
}
