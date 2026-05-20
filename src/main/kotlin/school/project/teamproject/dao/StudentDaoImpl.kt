package school.project.teamproject.dao

import org.springframework.stereotype.Repository
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.model.Student
import java.util.concurrent.atomic.AtomicLong

@Repository
class StudentDaoImpl : StudentDao {

    private val students = mutableListOf<Student>()
    private val idGenerator = AtomicLong(1)

    override fun create(request: StudentCreateRequest): Student {
        TODO("not implemented yet")
    }

    override fun getAll(): List<Student> {
        TODO("not implemented yet")
    }

    override fun getById(id: Long): Student? {
        TODO("not implemented yet")
    }

    override fun delete(id: Long) {
        TODO("not implemented yet")
    }
}