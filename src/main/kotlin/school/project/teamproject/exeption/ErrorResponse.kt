package school.project.teamproject.exception

import java.time.LocalDateTime

class ErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String
)