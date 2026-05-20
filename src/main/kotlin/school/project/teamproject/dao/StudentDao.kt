package school.project.teamproject.dao

import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.model.Student

interface StudentDao {
    fun create(request: StudentCreateRequest): Student
    fun getAll(): List<Student>
    fun getById(id: Long): Student?
    fun delete(id: Long)
}