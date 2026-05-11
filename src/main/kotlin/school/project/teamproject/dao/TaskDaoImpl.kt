package school.project.teamproject.dao

import org.springframework.stereotype.Repository
import school.project.teamproject.model.Task


@Repository
class TaskDaoImpl : TaskDao {
    override fun create(task: Task): Task {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): Task? {
        TODO("Not yet implemented")
    }

    override fun update(
        id: Long,
        task: Task
    ): Task? {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun getByTeacherId(teacherId: Long): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getTeacherByTaskId(id: Long): Task? {
        TODO("Not yet implemented")
    }


}