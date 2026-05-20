package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.dao.StudentDao
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.model.Student

@Service
class StudentServiceImpl(
    private val studentDao: StudentDao
) : StudentService {

    override fun create(request: StudentCreateRequest): Student = studentDao.create(request)

    override fun getAll(): List<Student> = studentDao.getAll()

    override fun getById(id: Long): Student? = studentDao.getById(id)

    override fun delete(id: Long) = studentDao.delete(id)
}