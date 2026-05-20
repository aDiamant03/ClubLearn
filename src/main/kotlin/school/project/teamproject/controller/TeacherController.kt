package school.project.teamproject.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.project.teamproject.model.Teacher
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/teachers")
class TeacherController {

    private val teachers = mutableListOf<Teacher>()
    private val idGenerator = AtomicLong(1)

    @PostMapping
    fun createTeacher(@RequestBody teacher: Teacher): ResponseEntity<Teacher> {
        val newTeacher = teacher.copy(id = idGenerator.getAndIncrement())
        teachers.add(newTeacher)

        return ResponseEntity.status(HttpStatus.CREATED).body(newTeacher)
    }

    @GetMapping
    fun getAllTeachers(): List<Teacher> {
        return teachers
    }

    @GetMapping("/{id}")
    fun getTeacherById(@PathVariable id: Long): ResponseEntity<Teacher> {
        val teacher = teachers.find { it.id == id }
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(teacher)
    }

    @PutMapping("/{id}")
    fun updateTeacher(
        @PathVariable id: Long,
        @RequestBody teacher: Teacher
    ): ResponseEntity<Teacher> {

        val index = teachers.indexOfFirst { it.id == id }

        if (index == -1) {
            return ResponseEntity.notFound().build()
        }

        val updatedTeacher = teacher.copy(id = id)
        teachers[index] = updatedTeacher

        return ResponseEntity.ok(updatedTeacher)
    }

    @DeleteMapping("/{id}")
    fun deleteTeacher(@PathVariable id: Long): ResponseEntity<String> {
        val deleted = teachers.removeIf { it.id == id }

        if (!deleted) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok("Teacher deleted")
    }
}