package com.example.Ejemplo.controller

import com.example.Ejemplo.dto.LoginRequest
import com.example.Ejemplo.dto.RegisterRequest
import com.example.Ejemplo.model.User
import com.example.Ejemplo.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class AuthController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody req: RegisterRequest): ResponseEntity<User> {
        if (userService.findByEmail(req.email) != null) {
            return ResponseEntity.badRequest().build()
        }
        val user = userService.register(
            User(
                name = req.name,
                email = req.email,
                address = req.address,
                password = req.password
            )
        )
        return ResponseEntity.ok(user) // devuelve el usuario creado
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody req: LoginRequest): ResponseEntity<User> {
        val user = userService.findByEmail(req.email)
            ?: return ResponseEntity.status(401).build()

        if (!userService.checkPassword(req.password, user.password)) {
            return ResponseEntity.status(401).build()
        }

        return ResponseEntity.ok(user) // devuelve el usuario autenticado
    }
}
