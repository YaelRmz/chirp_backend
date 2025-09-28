package com.yrmz.chirp.service.auth

import com.yrmz.chirp.domain.exception.InvalidCredentialsException
import com.yrmz.chirp.domain.exception.UserAlreadyExistsException
import com.yrmz.chirp.domain.exception.UserNotFoundException
import com.yrmz.chirp.domain.model.AuthenticatedUser
import com.yrmz.chirp.domain.model.User
import com.yrmz.chirp.domain.model.UserId
import com.yrmz.chirp.infra.database.entities.RefreshTokenEntity
import com.yrmz.chirp.infra.database.entities.UserEntity
import com.yrmz.chirp.infra.database.mappers.toUser
import com.yrmz.chirp.infra.database.repositories.RefreshTokenRepository
import com.yrmz.chirp.infra.database.repositories.UserRepository
import com.yrmz.chirp.infra.security.PasswordEncoder
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.time.Instant
import java.util.Base64

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun register(email: String, username: String, password: String): User {
        val user = userRepository.findByEmailOrUsername(
            email = email.trim(),
            username = username.trim(),
        )

        if (user != null) {
            throw UserAlreadyExistsException()
        }

        val savedUser = userRepository.save(
            UserEntity(
                email = email.trim(),
                username = username.trim(),
                hashedPassword = passwordEncoder.encode(password)
            )
        ).toUser()

        return savedUser
    }

    fun login(
        email: String,
        password: String
    ): AuthenticatedUser {
         val user = userRepository.findByEmail(email.trim())
             ?: throw InvalidCredentialsException()

        if(!passwordEncoder.matches(password, user.hashedPassword)) {
            throw InvalidCredentialsException()
        }

        // if(!user.hasVerifiedEmail)

        return user.id?.let { userId ->
            val accessToken = jwtService.generateAccessToken(userId)
            val refreshToken = jwtService.generateRefreshToken(userId)

            storeRefreshToken(userId, refreshToken)

            AuthenticatedUser(
                user = user.toUser(),
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        } ?: throw UserNotFoundException()
    }

    private fun storeRefreshToken(userId: UserId, token: String) {
        val hashed = hashToken(token)
        val expiryMs = jwtService.refreshTokenValidityMs
        val expiresAt = Instant.now().plusMillis(expiryMs)

        refreshTokenRepository.save(
            RefreshTokenEntity(
                userId = userId,
                expiresAt = expiresAt,
                hashedToken = hashed
            )
        )
    }

    private fun hashToken(token: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(token.encodeToByteArray())
        return Base64.getEncoder().encodeToString(hashBytes)
    }
}
