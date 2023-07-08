package com.patronus.userproduct.repository

import com.patronus.userproduct.model.Device
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DeviceRepository : JpaRepository<Device, Long>{

    override fun findById(id: Long): Optional<Device>
}

