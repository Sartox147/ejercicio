package com.example.Ejemplo.dto

import com.example.Ejemplo.model.NoticeCategory
import com.example.Ejemplo.model.NoticeStatus
import jakarta.validation.constraints.NotBlank

data class NoticeRequest(
    @field:NotBlank val title: String,
    @field:NotBlank val content: String,
    val category: NoticeCategory
)

data class NoticeUpdateRequest(
    val title: String? = null,
    val content: String? = null,
    val category: NoticeCategory? = null,
    val status: NoticeStatus? = null
)

data class CommentRequest(@field:NotBlank val content: String)
