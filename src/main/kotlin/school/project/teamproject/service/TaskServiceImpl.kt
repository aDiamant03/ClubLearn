package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.dao.TaskDao
import school.project.teamproject.model.Task

@Service
class TaskServiceImpl(private val taskDao: TaskDao) : TaskService {

    override fun create(task: Task): Task {
        return taskDao.create(task)
    }

    override fun getAll(): List<Task> {
        return taskDao.getAll()
    }

    override fun getById(id: Long): Task? {
        return taskDao.getById(id)
    }

    override fun update(id: Long, task: Task): Task? {
        return taskDao.update(id, task)
    }

    override fun delete(id: Long): Boolean {
        return taskDao.delete(id)
    }

    override fun getByTeacherId(teacherId: Long): List<Task> {
        return taskDao.getByTeacherId(teacherId)
    }

    override fun getTeacherByTaskId(id: Long): Task? {
        return taskDao.getTeacherByTaskId(id)
    }
}