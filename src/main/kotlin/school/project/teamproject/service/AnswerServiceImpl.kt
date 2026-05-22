package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.dao.AnswerDao
import school.project.teamproject.dao.TaskDao
import school.project.teamproject.model.Answer

@Service
class AnswerServiceImpl(
    private val answerDao: AnswerDao,
    private val taskDao: TaskDao
) : AnswerService {

    override fun create(answer: Answer): Answer {
        taskDao.getById(answer.taskId) ?: throw IllegalArgumentException("Task not found")
        return answerDao.create(answer)
    }

    override fun getAll(): List<Answer> = answerDao.getAll()

    override fun getById(id: Long): Answer? = answerDao.getById(id)

    override fun getByTaskId(taskId: Long): List<Answer> = answerDao.getByTaskId(taskId)

    override fun getByStudentId(studentId: Long): List<Answer> = answerDao.getByStudentId(studentId)

    override fun update(id: Long, answer: Answer): Answer? = answerDao.update(id, answer)

    override fun delete(id: Long): Boolean = answerDao.delete(id)
}
