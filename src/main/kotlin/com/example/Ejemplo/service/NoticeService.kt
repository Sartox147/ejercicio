package com.example.Ejemplo.service

import com.example.Ejemplo.model.Comment
import com.example.Ejemplo.model.Notice
import com.example.Ejemplo.model.NoticeCategory
import com.example.Ejemplo.model.NoticeStatus
import com.example.Ejemplo.repository.CommentRepository
import com.example.Ejemplo.repository.NoticeRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeService(
    private val noticeRepo: NoticeRepository,
    private val commentRepo: CommentRepository
) {
    private val log = LoggerFactory.getLogger(NoticeService::class.java)

    fun list(category: NoticeCategory?): List<Notice> =
        if (category == null) noticeRepo.findAll() else noticeRepo.findByCategory(category)

    @Transactional
    fun create(notice: Notice): Notice {
        val saved = noticeRepo.save(notice)
        log.info("Notificación: Nuevo aviso '${'$'}{saved.title}' para la comunidad de ${'$'}{saved.user?.address}")
        return saved
    }

    @Transactional
    fun update(id: Long, updater: (Notice) -> Unit): Notice {
        val n = noticeRepo.findById(id).orElseThrow { IllegalArgumentException("Aviso no encontrado") }
        updater(n)
        val saved = noticeRepo.save(n)
        if (saved.status == NoticeStatus.ATTENDED) {
            log.info("Notificación: Aviso #${'$'}{saved.id} marcado como ATENDIDO")
        }
        return saved
    }

    fun delete(id: Long) = noticeRepo.deleteById(id)

    fun addComment(notice: Notice, comment: Comment): Comment = commentRepo.save(comment)

    fun findById(id: Long) = noticeRepo.findById(id).orElse(null)

    fun commentsOf(n: Notice) = commentRepo.findByNotice(n)
}
