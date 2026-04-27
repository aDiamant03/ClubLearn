package school.project.teamproject.dto

data class StudentResponse(
    val id: Long?,
    val name: String,
    val surname: String,
    val email: String,
    val grade: Int? = null
)