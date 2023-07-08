package com.patronus.userproduct

import com.patronus.userproduct.controller.UserController
import com.patronus.userproduct.model.User
import com.patronus.userproduct.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.util.UriComponentsBuilder

@ExtendWith(MockitoExtension::class)
class UserControllerTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userController: UserController

    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(userController).build()


    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun createUser_Success() {
        // Arrange
        val user = User(1234, "Pranay", "Rao", "Germany",
            "1990-01-01")
        `when`(userRepository.save(user)).thenReturn(user)

        // Act
        val responseEntity: ResponseEntity<User> = userController.createUser(user, UriComponentsBuilder.newInstance())

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
        assertEquals(user, responseEntity.body)
        verify(userRepository, times(1)).save(user)
    }

    @Test
    fun getUsersWithDevices_Success() {
        val users = listOf(
            User(id = 1, firstName = "Pranay", lastName = "Rao", address = "123 Street", birthday = "1990-01-01"),
            User(id = 2, firstName = "Nani", lastName = "Rao", address = "456 Street", birthday = "1993-01-01")
        )

        Mockito.`when`(userRepository.findAll()).thenReturn(users)

        val request = get("/users/with-devices")

        mockMvc.perform(request)
            .andExpect(status().isOk)
            .andReturn()
    }

}


