package com.patronus.userproduct.controller

import com.patronus.userproduct.model.User
import com.patronus.userproduct.repository.DeviceRepository
import com.patronus.userproduct.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.logging.Logger

@RestController
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository, private val deviceRepository: DeviceRepository) {


    private val logger: Logger = Logger.getLogger(UserController::class.java.name)

    @PostMapping
    fun createUser(@RequestBody user: User, newInstance: UriComponentsBuilder): ResponseEntity<User> {
        logger.info("Creating user: $user")

        val savedUser = userRepository.save(user)
        logger.info("User created successfully: $savedUser")
        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }


    @PostMapping("/{userId}/assign-device/{deviceId}")
    fun assignDeviceToUser(@PathVariable userId: Long, @PathVariable deviceId: Long): ResponseEntity<String> {
        val user = userRepository.findById(userId)
        logger.info("Creating user: $userId")
        val device = deviceRepository.findById(deviceId)

        if (user.isPresent && device.isPresent) {
            user.get().device = device.get()
            userRepository.save(user.get())
            return ResponseEntity("Device assigned to the user successfully", HttpStatus.OK)
        } else {
            return ResponseEntity("User or device not found", HttpStatus.NOT_FOUND)
        }
    }


    @GetMapping("/with-devices")
    fun getUsersWithDevices(): ResponseEntity<List<User>> {
        val users = userRepository.findAll()
        return ResponseEntity(users, HttpStatus.OK)
    }
}
