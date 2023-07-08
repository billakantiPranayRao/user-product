package com.patronus.userproduct.controller

import com.patronus.userproduct.model.Device
import com.patronus.userproduct.repository.DeviceRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.logging.Logger

@RestController
@RequestMapping("/devices")
class DeviceController(private val deviceRepository: DeviceRepository) {

    private val logger: Logger = Logger.getLogger(DeviceController::class.java.name)
    @PostMapping
    fun createDevice(@RequestBody device: Device): ResponseEntity<Device> {

        logger.info("Device: $device")
        val savedDevice = deviceRepository.save(device)
        return ResponseEntity(savedDevice, HttpStatus.CREATED)
    }
}
