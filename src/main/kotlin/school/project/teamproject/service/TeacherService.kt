package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.model.Teacher
import java.util.concurrent.atomic.AtomicLong

@Service
class TeacherService {
    private val teachers = mutableListOf<Teacher>()
    private val idGenerator = AtomicLong(1)

    fun create(teacher: Teacher): Teacher {
        val newId = idGenerator.getAndIncrement()
        val newTeacher = teacher.copy(id = newId)
        teachers.add(newTeacher)
        return newTeacher
    }

    fun getAll(): List<Teacher> = teachers.toList()

    fun getById(id: Long): Teacher? = teachers.find { it.id == id }

    fun update(id: Long, updatedTeacher: Teacher): Teacher? {
        val index = teachers.indexOfFirst { it.id == id }
        return if (index != -1) {
            val teacherToUpdate = updatedTeacher.copy(id = id)
            teachers[index] = teacherToUpdate
            teacherToUpdate
        } else {
            null
        }
    }

    fun delete(id: Long): Boolean = teachers.removeIf { it.id == id }

    fun clear() {
        teachers.clear()
        idGenerator.set(1)
    }
}