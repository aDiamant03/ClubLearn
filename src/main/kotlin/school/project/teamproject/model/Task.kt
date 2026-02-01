package school.project.teamproject.model
import java.time.LocalDateTime
data class Task(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val answer: String?,
    val difficulty: String,
    val subject: String? = null,
    val topic: String? = null,
    val points: Int,
    val hint: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null
)
