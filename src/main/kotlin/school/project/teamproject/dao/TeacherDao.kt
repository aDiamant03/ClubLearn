package school.project.teamproject.dao

import school.project.teamproject.model.Teacher

interface TeacherDao {
    fun create(teacher: Teacher): Teacher
    fun getAll(): List<Teacher>
    fun getById(id: Long): Teacher?
    fun update(id: Long, updatedTeacher: Teacher): Teacher?
    fun delete(id: Long): Boolean
}