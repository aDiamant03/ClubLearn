package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.model.Student
import java.util.concurrent.atomic.AtomicLong

@Service
class StudentService {

    private val students = mutableListOf<Student>()
    private val idGenerator = AtomicLong(1)

    fun create(request: StudentCreateRequest): Student {
        val newId = idGenerator.getAndIncrement()
        val student = Student(
            id = newId,
            name = request.name,
            surname = request.surname,
            email = request.email,
            password = request.password,
            grade = request.grade
        )
        students.add(student)
        return student
    }

    fun getAll(): List<Student> = students.toList()

    fun delete(id: Long) {
        students.removeIf { it.id == id }
    }
}