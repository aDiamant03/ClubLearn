package school.project.teamproject.model

data class Teacher(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val subjects: List<String> = emptyList()
)
