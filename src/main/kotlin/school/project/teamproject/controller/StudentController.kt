package school.project.teamproject.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.dto.StudentResponse
import school.project.teamproject.model.Student
import school.project.teamproject.service.StudentService
import school.project.teamproject.mapper.toResponse

@RestController
@RequestMapping("/api/students")
class StudentController(val studentService: StudentService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun create(@RequestBody request: StudentCreateRequest): ResponseEntity<StudentResponse> {
        log.info("POST /api/students - создание студента: {}", request.email)
        val created = studentService.create(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(created.toResponse())
    }

    @GetMapping
    fun getAllStudents(): List<StudentResponse> {
        log.info("GET /api/students - список всех студентов")
        return studentService.getAll().map { it.toResponse() }
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long): ResponseEntity<Void> {
        log.info("DELETE /api/students/{} - попытка удаления", id)
        val deleted = studentService.delete(id)
        return if (deleted) {
            log.info("Студент с id {} удалён", id)
            ResponseEntity.noContent().build()
        } else {
            log.warn("Попытка удалить несуществующего студента id={}", id)
            ResponseEntity.notFound().build()
        }
    }
}