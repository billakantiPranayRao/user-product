package com.patronus.userproduct.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.springframework.data.annotation.Id
import java.util.*

@Entity
data class Device(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val serialNumber: String,
    val uuid: UUID,
    val phoneNumber: String,
    val model: String
) {

}
