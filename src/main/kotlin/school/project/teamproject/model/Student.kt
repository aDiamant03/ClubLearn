package school.project.teamproject.model

data class Student(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val grade: Int? = null
)
