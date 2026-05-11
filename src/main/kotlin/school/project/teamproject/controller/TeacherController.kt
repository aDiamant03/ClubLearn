package school.project.teamproject.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.project.teamproject.model.Teacher
import school.project.teamproject.service.TeacherService

@RestController
@RequestMapping("/api/teachers")
class TeacherController(private val teacherService: TeacherService) {

    @PostMapping
    fun createTeacher(@RequestBody teacher: Teacher): ResponseEntity<Teacher> {
        val createdTeacher = teacherService.create(teacher)
        return ResponseEntity(createdTeacher, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllTeachers(): ResponseEntity<List<Teacher>> {
        val teachers = teacherService.getAll()
        return ResponseEntity(teachers, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getTeacherById(@PathVariable id: Long): ResponseEntity<Teacher> {
        val teacher = teacherService.getById(id)
        return if (teacher != null) {
            ResponseEntity(teacher, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateTeacher(@PathVariable id: Long, @RequestBody teacher: Teacher): ResponseEntity<Teacher> {
        val updatedTeacher = teacherService.update(id, teacher)
        return if (updatedTeacher != null) {
            ResponseEntity(updatedTeacher, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTeacher(@PathVariable id: Long): ResponseEntity<Void> {
        return if (teacherService.delete(id)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}