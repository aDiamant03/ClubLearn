package school.project.teamproject.dao

import school.project.teamproject.model.Task

interface TaskDao {
    fun create(task: Task): Task
    fun getAll(): List<Task>
    fun getById(id: Long): Task?
    fun update(id: Long, task: Task): Task?
    fun delete(id: Long): Boolean
    fun getByTeacherId(teacherId: Long): List<Task>
    fun getTeacherByTaskId(id: Long): Task?
}