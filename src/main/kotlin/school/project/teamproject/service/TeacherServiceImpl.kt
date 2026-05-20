package school.project.teamproject.service
import org.springframework.stereotype.Service
import school.project.teamproject.dao.TeacherDao
import school.project.teamproject.model.Teacher

@Service
class TeacherServiceImpl(private val teacherDao: TeacherDao) : TeacherService
{
    override fun create(teacher: Teacher): Teacher {
        return teacherDao.create(teacher)
    }

    override fun getAll(): List<Teacher> {
        return teacherDao.getAll()
    }

    override fun getById(id: Long): Teacher? {
        return teacherDao.getById(id)
    }

    override fun update(id: Long, updatedTeacher: Teacher): Teacher? {
        return teacherDao.update(id, updatedTeacher)
    }

    override fun delete(id: Long): Boolean {
        return teacherDao.delete(id)
    }
}