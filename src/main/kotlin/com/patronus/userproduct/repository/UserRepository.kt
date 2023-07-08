package com.patronus.userproduct.repository

import com.patronus.userproduct.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>{
    // Method to fetch all users
    override fun findAll(): List<User>
}