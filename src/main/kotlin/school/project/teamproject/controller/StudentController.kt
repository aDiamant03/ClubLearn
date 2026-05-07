package school.project.teamproject.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import school.project.teamproject.exception.ResourceNotFoundException
import school.project.teamproject.model.Student
import school.project.teamproject.service.StudentService

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createStudent(@RequestBody student: Student): Student {

        if (student.name.isBlank()) {
            throw IllegalArgumentException("Student name cannot be empty")
        }

        return studentService.create(student)
    }

    @GetMapping
    fun getAllStudents(): List<Student> {
        return studentService.getAll()
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Long): Student {
        return studentService.getById(id)
            ?: throw ResourceNotFoundException("Student with id $id not found")
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long): String {

        val deleted = studentService.delete(id)

        if (!deleted) {
            throw ResourceNotFoundException("Student with id $id not found")
        }

        return "Student deleted successfully"
    }
}