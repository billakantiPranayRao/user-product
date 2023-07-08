package com.patronus.userproduct

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserProductApplication

fun main(args: Array<String>) {
	runApplication<UserProductApplication>(*args)
}
