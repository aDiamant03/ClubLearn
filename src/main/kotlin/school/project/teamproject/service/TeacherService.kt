package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.model.Teacher
import java.util.concurrent.atomic.AtomicLong


interface TeacherService {
    fun create(teacher: Teacher): Teacher
    fun getAll(): List<Teacher>
    fun getById(id: Long): Teacher?
    fun update(id: Long, updatedTeacher: Teacher): Teacher?
    fun delete(id: Long): Boolean
}