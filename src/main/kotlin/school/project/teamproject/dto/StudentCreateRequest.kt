package school.project.teamproject.dto

data class StudentCreateRequest (
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val grade: Int? = null
)

