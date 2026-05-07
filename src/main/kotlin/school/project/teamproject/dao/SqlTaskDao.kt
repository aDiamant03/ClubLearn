package school.project.teamproject.dao

import org.springframework.stereotype.Repository
import school.project.teamproject.model.Task

@Repository
class SqlTaskDao : TaskDao {

    override fun create(task: Task): Task =
        TODO("Implement with SQL")

    override fun findAll(): List<Task> =
        TODO("Implement with SQL")

    override fun findById(id: Long): Task? =
        TODO("Implement with SQL")

    override fun update(id: Long, task: Task): Task? =
        TODO("Implement with SQL")

    override fun delete(id: Long): Boolean =
        TODO("Implement with SQL")
}