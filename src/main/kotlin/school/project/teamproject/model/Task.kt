package school.project.teamproject.model

import java.time.LocalDateTime

data class Task(
    val id: Long? = null,
    val teacherId: Long? = null,
    val title: String,
    val description: String? = null,
    val correctAnswer: String? = null,
    val difficulty: String = "EASY",
    val subject: String? = null,
    val topic: String? = null,
    val points: Int = 0,
    val hint: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null
)
