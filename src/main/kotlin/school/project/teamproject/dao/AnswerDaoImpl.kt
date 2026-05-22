package school.project.teamproject.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import school.project.teamproject.model.Answer
import school.project.teamproject.model.AnswerStatus
import java.sql.PreparedStatement
import java.sql.Statement
import java.time.LocalDateTime

@Repository
class AnswerDaoImpl(
    private val jdbcTemplate: JdbcTemplate
) : AnswerDao {

    private val mapper = RowMapper<Answer> { rs, _ ->
        Answer(
            id = rs.getLong("id"),
            taskId = rs.getLong("task_id"),
            studentId = rs.getLong("student_id"),
            text = rs.getString("text"),
            status = AnswerStatus.valueOf(rs.getString("status")),
            teacherComment = rs.getString("teacher_comment"),
            score = rs.getObject("score") as Int?,
            createdAt = rs.getTimestamp("created_at").toLocalDateTime(),
            checkedAt = rs.getTimestamp("checked_at")?.toLocalDateTime()
        )
    }

    override fun create(answer: Answer): Answer {
        val keyHolder: KeyHolder = GeneratedKeyHolder()

        jdbcTemplate.update({ connection ->
            val ps: PreparedStatement = connection.prepareStatement(
                """
                INSERT INTO answers (task_id, student_id, text, status, teacher_comment, score)
                VALUES (?, ?, ?, ?, ?, ?)
                """.trimIndent(),
                Statement.RETURN_GENERATED_KEYS
            )
            ps.setLong(1, answer.taskId)
            ps.setLong(2, answer.studentId)
            ps.setString(3, answer.text)
            ps.setString(4, answer.status.name)
            ps.setString(5, answer.teacherComment)
            ps.setObject(6, answer.score)
            ps
        }, keyHolder)

        val id = keyHolder.keys?.get("id") as Number
        return getById(id.toLong())!!
    }

    override fun getAll(): List<Answer> {
        return jdbcTemplate.query("SELECT * FROM answers ORDER BY id", mapper)
    }

    override fun getById(id: Long): Answer? {
        return jdbcTemplate.query("SELECT * FROM answers WHERE id = ?", mapper, id).firstOrNull()
    }

    override fun getByTaskId(taskId: Long): List<Answer> {
        return jdbcTemplate.query("SELECT * FROM answers WHERE task_id = ? ORDER BY id", mapper, taskId)
    }

    override fun getByStudentId(studentId: Long): List<Answer> {
        return jdbcTemplate.query("SELECT * FROM answers WHERE student_id = ? ORDER BY id", mapper, studentId)
    }

    override fun update(id: Long, answer: Answer): Answer? {
        val checkedAt = if (answer.status == AnswerStatus.SENT) answer.checkedAt else LocalDateTime.now()
        val updated = jdbcTemplate.update(
            """
            UPDATE answers
            SET task_id = ?, student_id = ?, text = ?, status = ?, teacher_comment = ?, score = ?, checked_at = ?
            WHERE id = ?
            """.trimIndent(),
            answer.taskId,
            answer.studentId,
            answer.text,
            answer.status.name,
            answer.teacherComment,
            answer.score,
            checkedAt,
            id
        )
        return if (updated == 0) null else getById(id)
    }

    override fun delete(id: Long): Boolean {
        return jdbcTemplate.update("DELETE FROM answers WHERE id = ?", id) > 0
    }
}
