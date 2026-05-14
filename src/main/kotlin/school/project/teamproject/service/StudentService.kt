package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.model.Student
import school.project.teamproject.repository.StudentRepository

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun create(request: StudentCreateRequest): Student {
        val student = Student(
            name = request.name,
            surname = request.surname,
            email = request.email,
            password = request.password,
            grade = request.grade
        )
        return studentRepository.save(student)
    }

    fun getAll(): List<Student> = studentRepository.findAll()

    fun delete(id: Long) {
        studentRepository.deleteById(id)
    }
}