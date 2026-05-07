package school.project.teamproject.controller

import org.springframework.web.bind.annotation.*
import school.project.teamproject.model.Teacher
import school.project.teamproject.service.TeacherService

@RestController
@RequestMapping("/teachers")
class TeacherController(
    private val teacherService: TeacherService
) {

    @GetMapping
    fun getAllTeachers(): List<Teacher> {
        return teacherService.getAllTeachers()
    }

    @GetMapping("/{id}")
    fun getTeacherById(@PathVariable id: Long): Teacher {
        return teacherService.getTeacherById(id)
    }

    @PostMapping
    fun createTeacher(@RequestBody teacher: Teacher): Teacher {
        return teacherService.createTeacher(teacher)
    }

    @DeleteMapping("/{id}")
    fun deleteTeacher(@PathVariable id: Long) {
        teacherService.deleteTeacher(id)
    }
}