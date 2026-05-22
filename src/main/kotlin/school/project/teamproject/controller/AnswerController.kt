package school.project.teamproject.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import school.project.teamproject.model.Answer
import school.project.teamproject.service.AnswerService

@RestController
@RequestMapping("/api/answers")
class AnswerController(
    private val answerService: AnswerService
) {

    @PostMapping
    fun createAnswer(@RequestBody answer: Answer): ResponseEntity<Answer> {
        val createdAnswer = answerService.create(answer)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswer)
    }

    @GetMapping
    fun getAllAnswers(): List<Answer> {
        return answerService.getAll()
    }

    @GetMapping("/{id}")
    fun getAnswerById(@PathVariable id: Long): ResponseEntity<Answer> {
        val answer = answerService.getById(id)
        return if (answer == null) ResponseEntity.notFound().build() else ResponseEntity.ok(answer)
    }

    @GetMapping("/task/{taskId}")
    fun getAnswersByTask(@PathVariable taskId: Long): List<Answer> {
        return answerService.getByTaskId(taskId)
    }

    @GetMapping("/student/{studentId}")
    fun getAnswersByStudent(@PathVariable studentId: Long): List<Answer> {
        return answerService.getByStudentId(studentId)
    }

    @PutMapping("/{id}")
    fun updateAnswer(
        @PathVariable id: Long,
        @RequestBody answer: Answer
    ): ResponseEntity<Answer> {
        val updatedAnswer = answerService.update(id, answer)
        return if (updatedAnswer == null) ResponseEntity.notFound().build() else ResponseEntity.ok(updatedAnswer)
    }

    @DeleteMapping("/{id}")
    fun deleteAnswer(@PathVariable id: Long): ResponseEntity<Void> {
        return if (answerService.delete(id)) ResponseEntity.noContent().build() else ResponseEntity.notFound().build()
    }
}
