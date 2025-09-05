package com.example.Ejemplo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {
    @GetMapping("/")
    fun hello() = mapOf("status" to "ok", "name" to "vecindario-api")
}
