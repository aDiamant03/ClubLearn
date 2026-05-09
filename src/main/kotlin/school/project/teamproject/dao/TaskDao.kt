package school.project.teamproject.dao

import org.springframework.stereotype.Repository
import school.project.teamproject.model.Task

@Repository
class TaskDao {
    fun save(task: Task): Task = task
    fun findAll(): List<Task> = emptyList()
    fun findById(id: Long): Task? = null
    fun update(id: Long, task: Task): Task? = null
    fun delete(id: Long): Boolean = false
    fun findByTeacherId(teacherId: Long): List<Task> = emptyList()
    fun findTeacherByTaskId(taskId: Long): Task? = null
}