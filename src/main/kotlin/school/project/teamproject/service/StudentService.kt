package school.project.teamproject.service
import org.springframework.stereotype.Service
import school.project.teamproject.model.Student
import java.util.concurrent.atomic.AtomicLong

@Service
class StudentService {
    private val students = mutableListOf<Student>()
    private val idGenerator = AtomicLong(1)

    fun create(student: Student): Student {
        val newId = idGenerator.getAndIncrement()
        val newStudent = student.copy(id = newId)
        students.add(newStudent)
        return newStudent
    }
    fun getAll(): List<Student> = students.toList()
    fun getById(id: Long): Student? = students.find { it.id == id }
    fun delete(id: Long): Boolean = students.removeIf { it.id == id }
    fun clear() {
        students.clear()
        idGenerator.set(1)
    }
}