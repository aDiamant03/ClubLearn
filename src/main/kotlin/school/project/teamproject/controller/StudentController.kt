package school.project.teamproject.controller

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

    @PostMapping
    fun create(@RequestBody request: StudentCreateRequest): ResponseEntity<StudentResponse> {
        val tempStudent = Student(
            id = 0,
            name = request.name,
            surname = request.surname,
            email = request.email,
            password = request.password,
            grade = request.grade
        )
        val createdStudent = studentService.create(tempStudent)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent.toResponse())
    }

    @GetMapping
    fun getAllStudents(): List<StudentResponse> {
        return studentService.getAll().map { it.toResponse() }
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long): ResponseEntity<Void> {
        val deleted = studentService.delete(id)
        return if (deleted) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}