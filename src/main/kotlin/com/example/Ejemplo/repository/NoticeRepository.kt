package com.example.Ejemplo.repository

import com.example.Ejemplo.model.Notice
import com.example.Ejemplo.model.NoticeCategory
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository : JpaRepository<Notice, Long> {
    fun findByCategory(category: NoticeCategory): List<Notice>
}
