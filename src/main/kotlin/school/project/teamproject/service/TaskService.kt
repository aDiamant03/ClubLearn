package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.exception.BadRequestException
import school.project.teamproject.exception.ResourceNotFoundException
import school.project.teamproject.model.Task

@Service
class TaskService {

    private val tasks = mutableListOf<Task>()

    fun getAllTasks(): List<Task> {
        return tasks
    }

    fun getTaskById(id: Long): Task {
        return tasks.find { it.id == id }
            ?: throw ResourceNotFoundException("Task with id $id not found")
    }

    fun createTask(task: Task): Task {
        if (task.title.isBlank()) {
            throw BadRequestException("Task title cannot be empty")
        }

        tasks.add(task)
        return task
    }

    fun deleteTask(id: Long) {
        val task = getTaskById(id)
        tasks.remove(task)
    }
}