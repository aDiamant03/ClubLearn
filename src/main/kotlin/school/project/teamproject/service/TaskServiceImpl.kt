package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.dao.TaskDao
import school.project.teamproject.model.Task

@Service
class TaskServiceImpl(private val taskDao: TaskDao) : TaskService {

    override fun create(task: Task): Task {
        TODO("be implemented with sql task")
    }

    override fun getAll(): List<Task> {
        TODO("be implemented with sql task")
    }

    override fun getById(id: Long): Task? {
        TODO("be implemented with sql task")
    }

    override fun update(id: Long, task: Task): Task? {
        TODO("be implemented with sql task")
    }

    override fun delete(id: Long): Boolean {
        TODO("be implemented with sql task")
    }

    override fun getByTeacherId(teacherId: Long): List<Task> {
        TODO("be implemented with sql task")
    }

    override fun getTeacherByTaskId(id: Long): Task? {
        TODO("be implemented with sql task")
    }
}