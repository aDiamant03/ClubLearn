package school.project.teamproject.service

import school.project.teamproject.dao.TaskDao
import school.project.teamproject.model.Task
import java.time.LocalDateTime

class TaskService(private val taskDao: TaskDao) {

    fun create(task: Task): Task {
        val prepared = task.copy(
            createdAt = LocalDateTime.now(),
            updatedAt = null
        )
        return taskDao.create(prepared)
    }

    fun getAll() = taskDao.findAll()

    fun getById(id: Long) = taskDao.findById(id)

    fun update(id: Long, updatedTask: Task): Task? {
        val prepared = updatedTask.copy(
            updatedAt = LocalDateTime.now()
        )
        return taskDao.update(id, prepared)
    }

    fun delete(id: Long) = taskDao.delete(id)
}