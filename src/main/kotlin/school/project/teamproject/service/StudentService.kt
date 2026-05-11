package school.project.teamproject.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.model.Student
import school.project.teamproject.repository.StudentRepository

@Service
@Transactional
class StudentService(private val studentRepository: StudentRepository) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun create(request: StudentCreateRequest): Student {
        log.info("Создание студента: email={}", request.email)
        val student = Student(
            name = request.name,
            surname = request.surname,
            email = request.email,
            password = request.password,
            grade = request.grade
        )
        val saved = studentRepository.save(student)
        log.info("Студент создан с id={}", saved.id)
        return saved
    }

    fun getAll(): List<Student> = studentRepository.findAll()
    fun getById(id: Long): Student? = studentRepository.findById(id).orElse(null)
    fun delete(id: Long): Boolean {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id)
            return true
        }
        return false
    }
}