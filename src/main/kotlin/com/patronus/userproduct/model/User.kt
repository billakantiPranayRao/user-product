package com.patronus.userproduct.model

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.OneToOne
import org.springframework.data.annotation.Id

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val address: String,
    val birthday: String,

    @OneToOne
    var device: Device? = null
) {


}
