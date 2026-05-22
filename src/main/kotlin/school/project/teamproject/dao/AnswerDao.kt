package school.project.teamproject.dao

import school.project.teamproject.model.Answer

interface AnswerDao {
    fun create(answer: Answer): Answer
    fun getAll(): List<Answer>
    fun getById(id: Long): Answer?
    fun getByTaskId(taskId: Long): List<Answer>
    fun getByStudentId(studentId: Long): List<Answer>
    fun update(id: Long, answer: Answer): Answer?
    fun delete(id: Long): Boolean
}
