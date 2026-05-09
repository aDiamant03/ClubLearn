package school.project.teamproject.dao

import org.springframework.stereotype.Repository
import school.project.teamproject.model.Teacher

@Repository
class TeacherDaoImpl : TeacherDao {
    override fun create(teacher: Teacher): Teacher {
        TODO("be implemented with sql task")
    }

    override fun getAll(): List<Teacher> {
        TODO("be implemented with sql task")
    }

    override fun getById(id: Long): Teacher? {
        TODO("be implemented with sql task")
    }

    override fun update(
        id: Long,
        updatedTeacher: Teacher
    ): Teacher? {
        TODO("be implemented with sql task")
    }

    override fun delete(id: Long): Boolean {
        TODO("be implemented with sql task")
    }
}