package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.exception.BadRequestException
import school.project.teamproject.exception.ResourceNotFoundException
import school.project.teamproject.model.Teacher

@Service
class TeacherService {

    private val teachers = mutableListOf<Teacher>()

    fun getAllTeachers(): List<Teacher> {
        return teachers
    }

    fun getTeacherById(id: Long): Teacher {
        return teachers.find { it.id == id }
            ?: throw ResourceNotFoundException("Teacher with id $id not found")
    }

    fun createTeacher(teacher: Teacher): Teacher {
        if (teacher.name.isBlank()) {
            throw BadRequestException("Teacher name cannot be empty")
        }

        teachers.add(teacher)
        return teacher
    }

    fun deleteTeacher(id: Long) {
        val teacher = getTeacherById(id)
        teachers.remove(teacher)
    }
}