package com.example.Ejemplo.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterRequest(
    @field:NotBlank val name: String,
    @field:Email val email: String,
    @field:NotBlank val address: String,
    @field:NotBlank val password: String
)

data class LoginRequest(
    @field:Email val email: String,
    @field:NotBlank val password: String
)

// ❌ Eliminamos el token
// data class AuthResponse(val token: String)

// ✅ Opciones sin token:
data class AuthResponse(
    val message: String,
    val userId: Long? = null
)
