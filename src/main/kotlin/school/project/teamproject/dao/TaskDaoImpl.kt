package school.project.teamproject.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import school.project.teamproject.model.Task
import java.sql.PreparedStatement
import java.sql.Statement
import java.time.LocalDateTime

@Repository
class TaskDaoImpl(
    private val jdbcTemplate: JdbcTemplate
) : TaskDao {

    private val mapper = RowMapper<Task> { rs, _ ->
        Task(
            id = rs.getLong("id"),
            teacherId = rs.getObject("teacher_id") as Long?,
            title = rs.getString("title"),
            description = rs.getString("description"),
            correctAnswer = rs.getString("correct_answer"),
            difficulty = rs.getString("difficulty"),
            subject = rs.getString("subject"),
            topic = rs.getString("topic"),
            points = rs.getInt("points"),
            hint = rs.getString("hint"),
            createdAt = rs.getTimestamp("created_at").toLocalDateTime(),
            updatedAt = rs.getTimestamp("updated_at")?.toLocalDateTime()
        )
    }

    override fun create(task: Task): Task {
        val keyHolder: KeyHolder = GeneratedKeyHolder()

        jdbcTemplate.update({ connection ->
            val ps: PreparedStatement = connection.prepareStatement(
                """
                INSERT INTO tasks
                (teacher_id, title, description, correct_answer, difficulty, subject, topic, points, hint)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """.trimIndent(),
                Statement.RETURN_GENERATED_KEYS
            )
            ps.setObject(1, task.teacherId)
            ps.setString(2, task.title)
            ps.setString(3, task.description)
            ps.setString(4, task.correctAnswer)
            ps.setString(5, task.difficulty)
            ps.setString(6, task.subject)
            ps.setString(7, task.topic)
            ps.setInt(8, task.points)
            ps.setString(9, task.hint)
            ps
        }, keyHolder)

        val id = keyHolder.keys?.get("id") as Number
        return getById(id.toLong())!!
    }

    override fun getAll(): List<Task> {
        return jdbcTemplate.query("SELECT * FROM tasks ORDER BY id", mapper)
    }

    override fun getById(id: Long): Task? {
        return jdbcTemplate.query("SELECT * FROM tasks WHERE id = ?", mapper, id).firstOrNull()
    }

    override fun update(id: Long, task: Task): Task? {
        val updated = jdbcTemplate.update(
            """
            UPDATE tasks
            SET teacher_id = ?, title = ?, description = ?, correct_answer = ?, difficulty = ?,
                subject = ?, topic = ?, points = ?, hint = ?, updated_at = ?
            WHERE id = ?
            """.trimIndent(),
            task.teacherId,
            task.title,
            task.description,
            task.correctAnswer,
            task.difficulty,
            task.subject,
            task.topic,
            task.points,
            task.hint,
            LocalDateTime.now(),
            id
        )
        return if (updated == 0) null else getById(id)
    }

    override fun delete(id: Long): Boolean {
        return jdbcTemplate.update("DELETE FROM tasks WHERE id = ?", id) > 0
    }

    override fun getByTeacherId(teacherId: Long): List<Task> {
        return jdbcTemplate.query("SELECT * FROM tasks WHERE teacher_id = ? ORDER BY id", mapper, teacherId)
    }

    override fun getTeacherByTaskId(id: Long): Task? {
        return getById(id)
    }
}
