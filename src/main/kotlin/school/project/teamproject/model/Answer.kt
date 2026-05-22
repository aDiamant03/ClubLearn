package school.project.teamproject.model

import java.time.LocalDateTime

enum class AnswerStatus {
    SENT,
    ACCEPTED,
    REJECTED
}

data class Answer(
    val id: Long? = null,
    val taskId: Long,
    val studentId: Long,
    val text: String,
    val status: AnswerStatus = AnswerStatus.SENT,
    val teacherComment: String? = null,
    val score: Int? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val checkedAt: LocalDateTime? = null
)
