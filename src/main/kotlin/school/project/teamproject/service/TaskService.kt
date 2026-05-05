package school.project.teamproject.service


import org.springframework.stereotype.Service
import school.project.teamproject.model.Task
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

@Service
class TaskService {
    private val tasks = mutableListOf<Task>()
    private val idGenerator = AtomicLong(1)

    fun create(task: Task): Task {
        val newId = idGenerator.getAndIncrement()
        val newTask = task.copy(id = newId, createdAt = LocalDateTime.now(), updatedAt = null)
        tasks.add(newTask)
        return newTask
    }

    fun getAll(): List<Task> = tasks.toList()

    fun getById(id: Long): Task? = tasks.find { it.id == id }

    fun update(id: Long, updatedTask: Task): Task? {
        val index = tasks.indexOfFirst { it.id == id }
        return if (index != -1) {
            val existingTask = tasks[index]
            val taskToUpdate = existingTask.copy(
                teacherId = updatedTask.teacherId, // Обновляем teacherId
                title = updatedTask.title,
                description = updatedTask.description,
                answer = updatedTask.answer,
                difficulty = updatedTask.difficulty,
                subject = updatedTask.subject,
                topic = updatedTask.topic,
                points = updatedTask.points,
                hint = updatedTask.hint,
                updatedAt = LocalDateTime.now()
            )
            tasks[index] = taskToUpdate
            taskToUpdate
        } else {
            null
        }
    }

    fun delete(id: Long): Boolean = tasks.removeIf { it.id == id }

    fun clear() {
        tasks.clear()
        idGenerator.set(1)
    }
}