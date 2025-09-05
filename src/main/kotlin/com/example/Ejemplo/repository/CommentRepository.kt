package com.example.Ejemplo.repository

import com.example.Ejemplo.model.Comment
import com.example.Ejemplo.model.Notice
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByNotice(notice: Notice): List<Comment>
}
