package com.example.Ejemplo.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.Instant

@Entity
@Table(name = "comments")
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "notice_id")
    var notice: Notice? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    var user: User? = null,
    @field:NotBlank var content: String = "",
    var createdAt: Instant = Instant.now()
)
