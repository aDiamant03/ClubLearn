package school.project.teamproject.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import school.project.teamproject.model.Student
import java.util.concurrent.atomic.AtomicLong

@Service
class StudentService {
    private val log = LoggerFactory.getLogger(javaClass)

    private val students = mutableListOf<Student>()
    private val idGenerator = AtomicLong(1)

    fun create(student: Student): Student {
        log.info("Создание студента: name={}, email={}", student.name, student.email)
        val newId = idGenerator.getAndIncrement()
        val newStudent = student.copy(id = newId)
        students.add(newStudent)
        log.info("Студент создан с id={}", newId)
        return newStudent
    }

    fun getAll(): List<Student> {
        log.debug("Запрос списка всех студентов (всего {})", students.size)
        return students.toList()
    }

    fun getById(id: Long): Student? {
        log.debug("Поиск студента по id={}", id)
        val student = students.find { it.id == id }
        if (student == null) {
            log.warn("Студент с id={} не найден", id)
        } else {
            log.debug("Студент найден: name={}", student.name)
        }
        return student
    }

    fun delete(id: Long): Boolean {
        log.info("Попытка удаления студента с id={}", id)
        val removed = students.removeIf { it.id == id }
        if (removed) {
            log.info("Студент с id={} удалён", id)
        } else {
            log.warn("Не удалось удалить студента с id={} (не существует)", id)
        }
        return removed
    }

    fun clear() {
        log.warn("Очистка всего списка студентов (было {})", students.size)
        students.clear()
        idGenerator.set(1)
        log.info("Список студентов очищен, счётчик сброшен")
    }
}