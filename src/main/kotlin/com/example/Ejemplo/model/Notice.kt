package com.example.Ejemplo.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.Instant

@Entity
@Table(name = "notices")
data class Notice(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @field:NotBlank var title: String = "",
    @field:NotBlank var content: String = "",
    @Enumerated(EnumType.STRING) var category: NoticeCategory = NoticeCategory.AYUDA,
    @Enumerated(EnumType.STRING) var status: NoticeStatus = NoticeStatus.OPEN,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    var user: User? = null,
    var createdAt: Instant = Instant.now()
)

enum class NoticeCategory { AYUDA, ALERTA, COMPRAS, REUNIONES }
enum class NoticeStatus { OPEN, ATTENDED }
