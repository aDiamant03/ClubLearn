package school.project.teamproject.dao

import school.project.teamproject.model.Task

interface TaskDao {
    fun create(task: Task): Task
    fun findAll(): List<Task>
    fun findById(id: Long): Task?
    fun update(id: Long, task: Task): Task?
    fun delete(id: Long): Boolean
}