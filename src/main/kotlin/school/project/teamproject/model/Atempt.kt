package school.project.teamproject.model
import java.time.LocalDateTime
data class Atempt(
    val id: Long,
    val student: Student,
    val task: Task,
    val submittedAnswer: String,
    val status: Boolean,
    val score: Int = 0,
    val hintsUsed: Int = 0,
    val timeSpentSeconds: Long? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val solvedAt: LocalDateTime? = null
)
