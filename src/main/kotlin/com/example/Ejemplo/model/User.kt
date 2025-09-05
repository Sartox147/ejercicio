package com.example.Ejemplo.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank val name: String = "",
    @field:Email @field:NotBlank val email: String = "",
    @field:NotBlank val address: String = "",
    @field:NotBlank var password: String = ""
)
