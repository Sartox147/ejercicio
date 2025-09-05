package com.example.Ejemplo.controller

import com.example.Ejemplo.model.Comment
import com.example.Ejemplo.model.Notice
import com.example.Ejemplo.model.NoticeCategory
import com.example.Ejemplo.dto.CommentRequest
import com.example.Ejemplo.dto.NoticeRequest
import com.example.Ejemplo.dto.NoticeUpdateRequest
import com.example.Ejemplo.service.NoticeService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notices")
class NoticeController(
    private val noticeService: NoticeService
) {
    @GetMapping
    fun list(@RequestParam(required = false) category: NoticeCategory?): List<Notice> =
        noticeService.list(category)

    @PostMapping
    fun create(@Valid @RequestBody req: NoticeRequest): ResponseEntity<Notice> {
        val notice = Notice(
            title = req.title,
            content = req.content,
            category = req.category,
            user = null // ya no vinculamos usuario porque no hay autenticación
        )
        return ResponseEntity.ok(noticeService.create(notice))
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody req: NoticeUpdateRequest
    ): ResponseEntity<Notice> {
        val updated = noticeService.update(id) { n ->
            req.title?.let { n.title = it }
            req.content?.let { n.content = it }
            //req.category?.let { n.category = it }
            //req.status?.let { n.status = it }
        }
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        noticeService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}/comments")
    fun comments(@PathVariable id: Long): ResponseEntity<Any> {
        val notice = noticeService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(noticeService.commentsOf(notice))
    }

    @PostMapping("/{id}/comments")
    fun addComment (
        @PathVariable id: Long,
        @Valid @RequestBody req: CommentRequest
    ): ResponseEntity<Any> {
        val notice = noticeService.findById(id) ?: return ResponseEntity.notFound().build()
        val comment = Comment(notice = notice, user = null, content = req.content)
        return ResponseEntity.ok(noticeService.addComment(notice, comment))
    }
}
