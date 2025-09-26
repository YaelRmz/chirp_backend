package com.yrmz.chirp

import com.yrmz.chirp.infra.database.entities.UserEntity
import com.yrmz.chirp.infra.database.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import kotlin.time.ExperimentalTime

@SpringBootApplication
class ChirpApplication

fun main(args: Array<String>) {
	runApplication<ChirpApplication>(*args)
}

@Component
class Demo(
	private val repository: UserRepository
) {
	@OptIn(ExperimentalTime::class)
    @PostConstruct
	fun init() {
		repository.save(
			UserEntity(
				email = "test@tes.com",
				username = "test",
				hashedPassword = "123"
			)
		)
	}
}
