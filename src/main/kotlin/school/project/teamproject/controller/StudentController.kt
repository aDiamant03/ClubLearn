package school.project.teamproject.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.dto.StudentResponse
import school.project.teamproject.service.StudentService
import school.project.teamproject.mapper.toResponse

@RestController
@RequestMapping("/api/students")
class StudentController(private val studentService: StudentService) {

    @PostMapping
    fun create(@RequestBody request: StudentCreateRequest): ResponseEntity<StudentResponse> {
        val student = studentService.create(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(student.toResponse())
    }

    @GetMapping
    fun getAll(): List<StudentResponse> = studentService.getAll().map { it.toResponse() }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        studentService.delete(id)
        return ResponseEntity.noContent().build()
    }
}