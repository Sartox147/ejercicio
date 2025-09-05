package com.example.Ejemplo.service

import com.example.Ejemplo.model.User
import com.example.Ejemplo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repo: UserRepository) {

    fun register(user: User): User {
        // Guarda la contraseña tal cual
        return repo.save(user)
    }

    fun checkPassword(raw: String, saved: String): Boolean {
        // Compara directamente
        return raw == saved
    }

    fun findByEmail(email: String): User? = repo.findByEmail(email).orElse(null)
}
